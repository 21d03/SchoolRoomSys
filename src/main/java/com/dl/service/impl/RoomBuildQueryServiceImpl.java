package com.dl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.common.exception.ServiceException;
import com.dl.entity.dto.RoomBuildAddDTO;
import com.dl.entity.dto.RoomBuildQueryDTO;
import com.dl.entity.pojo.HouseMaster;
import com.dl.entity.pojo.RoomBuild;
import com.dl.entity.pojo.RoomBuildDetails;
import com.dl.entity.pojo.StudentInfo;
import com.dl.entity.vo.RoomBuildVO;
import com.dl.entity.vo.RoomBuildStatusResultVO;
import com.dl.mapper.HouseMasterMapper;
import com.dl.mapper.RoomBuildDetailsMapper;
import com.dl.mapper.RoomBuildMapper;
import com.dl.mapper.StudentInfoMapper;
import com.dl.service.RoomBuildService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RoomBuildQueryServiceImpl implements RoomBuildService {
    
    @Autowired
    private RoomBuildMapper roomBuildMapper;
    
    @Autowired
    private RoomBuildDetailsMapper roomBuildDetailsMapper;
    
    @Autowired
    private HouseMasterMapper houseMasterMapper;
    
    @Autowired
    private StudentInfoMapper studentInfoMapper;
    
    @Override
    public IPage<RoomBuildVO> queryRoomBuildPage(RoomBuildQueryDTO queryDTO) {
        Page<RoomBuildVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        return roomBuildMapper.queryRoomBuildPage(page, queryDTO.getBuildName(), queryDTO.getIsUsed());
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addRoomBuild(RoomBuildAddDTO addDTO) {
        // 检查宿舍楼ID是否已存在
        RoomBuild existingBuild = roomBuildMapper.selectById(addDTO.getBuildId());
        if (existingBuild != null) {
            throw new ServiceException("该宿舍楼ID已存在，请使用其他ID");
        }

        // 构建宿舍楼对象并保存
        RoomBuild roomBuild = new RoomBuild();
        BeanUtils.copyProperties(addDTO, roomBuild);
        // 设置固定值
        roomBuild.setDetailsTable("room_build_details");
        boolean success = roomBuildMapper.insert(roomBuild) > 0;
        if (!success) {
            throw new ServiceException("保存宿舍楼信息失败");
        }

        // 更新宿管的build_id
        if (addDTO.getHmId() != null && !addDTO.getHmId().isEmpty()) {
            HouseMaster houseMaster = houseMasterMapper.selectById(addDTO.getHmId());
            if (houseMaster != null) {
                if (houseMaster.getBuildId() != null && !houseMaster.getBuildId().isEmpty()) {
                    throw new ServiceException("该宿管已分配其他宿舍楼，请选择其他宿管");
                }
                houseMaster.setBuildId(addDTO.getBuildId());
                houseMasterMapper.updateById(houseMaster);
            }
        }

        // 批量保存房间详情
        try {
            List<RoomBuildDetails> detailsList = new ArrayList<>();
            
            // 获取楼层数和总房间数
            int layerNumber = Integer.parseInt(addDTO.getLayerNumber());
            int totalRoomNum = Integer.parseInt(addDTO.getTotalRoomNum());
            
            // 计算每层房间数和余数
            int baseRoomsPerLayer = totalRoomNum / layerNumber;
            int remainingRooms = totalRoomNum % layerNumber;
            
            log.info("宿舍楼{}：共{}层，总计{}间宿舍，基础每层{}间，余数{}间", 
                    addDTO.getBuildId(), layerNumber, totalRoomNum, baseRoomsPerLayer, remainingRooms);
            
            // 为每一层生成房间详情
            for (int layer = 1; layer <= layerNumber; layer++) {
                // 确定当前层的房间数（最高层分配余数房间）
                int currentLayerRooms = (layer == layerNumber) ? 
                        baseRoomsPerLayer + remainingRooms : baseRoomsPerLayer;
                
                log.info("处理第{}层，分配{}间房间", layer, currentLayerRooms);
                
                for (int room = 1; room <= currentLayerRooms; room++) {
                    RoomBuildDetails details = new RoomBuildDetails();
                    
                    // 设置基本信息
                    details.setBuildId(addDTO.getBuildId());
                    details.setBuildName(addDTO.getBuildName());
                    details.setLayerNumber(String.valueOf(layer));
                    
                    // 生成房间号：楼层 + 房间序号（两位数，不足补0）
                    String roomNumber = String.format("%d%02d", layer, room);
                    details.setRoomId(roomNumber);
                    
                    // 设置默认值
                    details.setIsMixed("2");       // 默认不混寝
                    details.setRoomType("6");      // 默认6人寝
                    details.setStatus("1");        // 默认正常使用
                    
                    // college_ids和manage_teacher_id置为空
                    details.setCollegeIds("");
                    details.setManageTeacherId("");
                    
                    detailsList.add(details);
                }
            }
            
            // 批量保存房间详情
            if (!detailsList.isEmpty()) {
                if (!roomBuildDetailsMapper.insertBatch(detailsList)) {
                    throw new ServiceException("保存房间详情失败");
                }
                log.info("成功插入{}条房间详情记录", detailsList.size());
            }
        } catch (NumberFormatException e) {
            log.error("楼层数或房间总数格式错误", e);
            throw new ServiceException("楼层数或房间总数格式错误");
        } catch (Exception e) {
            log.error("生成房间详情失败", e);
            throw new ServiceException("生成房间详情失败：" + e.getMessage());
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteRoomBuild(String buildId) {
        if (buildId == null || buildId.isEmpty()) {
            throw new ServiceException("宿舍楼ID不能为空");
        }

        // 1. 检查宿舍楼是否存在
        RoomBuild roomBuild = roomBuildMapper.selectById(buildId);
        if (roomBuild == null) {
            throw new ServiceException("宿舍楼不存在");
        }

        // 2. 更新宿管表 - 清空关联到该宿舍楼的宿管
        try {
            // 步骤1：查询关联到该宿舍楼的宿管ID
            QueryWrapper<HouseMaster> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("hm_id").eq("build_id", buildId);
            List<Object> hmIds = houseMasterMapper.selectObjs(queryWrapper);
            
            // 检查是否找到了宿管
            if (hmIds != null && !hmIds.isEmpty()) {
                log.info("找到 {} 个关联宿舍楼ID {} 的宿管", hmIds.size(), buildId);
                
                // 步骤2：对每个宿管ID，更新其build_id为null
                for (Object hmIdObj : hmIds) {
                    String hmId = hmIdObj.toString();
                    log.info("准备更新宿管 {}", hmId);
                    
                    // 通过SQL直接更新
                    int updateCount = houseMasterMapper.clearBuildId(hmId);
                    
                    if (updateCount <= 0) {
                        log.error("更新宿管 {} 信息失败", hmId);
                        throw new ServiceException("更新宿管信息失败");
                    }
                    
                    log.info("宿管 {} 更新成功", hmId);
                }
            } else {
                log.info("没有找到关联宿舍楼ID {} 的宿管", buildId);
            }
        } catch (Exception e) {
            log.error("更新宿管信息失败", e);
            throw new ServiceException("更新宿管信息失败：" + e.getMessage());
        }

        // 3. 删除room_build_details表中的数据
        QueryWrapper<RoomBuildDetails> detailsWrapper = new QueryWrapper<>();
        detailsWrapper.eq("build_id", buildId);
        int detailsResult = roomBuildDetailsMapper.delete(detailsWrapper);
        log.info("删除宿舍楼详情记录 {} 条", detailsResult);

        // 4. 删除room_build表中的数据
        int buildResult = roomBuildMapper.deleteById(buildId);
        if (buildResult <= 0) {
            throw new ServiceException("删除宿舍楼失败");
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoomBuildStatusResultVO updateRoomBuildStatus(String buildId, String isUsed) {
        if (buildId == null || buildId.isEmpty()) {
            return RoomBuildStatusResultVO.fail("宿舍楼ID不能为空");
        }
        
        // 1. 检查宿舍楼是否存在
        RoomBuild roomBuild = roomBuildMapper.selectById(buildId);
        if (roomBuild == null) {
            return RoomBuildStatusResultVO.fail("宿舍楼不存在");
        }
        
        // 2. 如果要停用宿舍楼，检查是否有学生居住
        if ("0".equals(isUsed)) {
            // 查询学生信息表中是否有关联到该宿舍楼的学生
            QueryWrapper<StudentInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("build_id", buildId);
            Long studentCount = studentInfoMapper.selectCount(queryWrapper);
            
            if (studentCount != null && studentCount > 0) {
                return RoomBuildStatusResultVO.fail("宿舍楼内还有" + studentCount + "名学生居住，无法停用");
            }
        }
        
        // 3. 更新宿舍楼状态
        try {
            roomBuild.setIsUsed(isUsed);
            int result = roomBuildMapper.updateById(roomBuild);
            if (result > 0) {
                String statusText = "1".equals(isUsed) ? "启用" : "停用";
                log.info("宿舍楼{}状态更新为{}", buildId, statusText);
                return RoomBuildStatusResultVO.success();
            } else {
                return RoomBuildStatusResultVO.fail("更新宿舍楼状态失败");
            }
        } catch (Exception e) {
            log.error("更新宿舍楼状态异常", e);
            throw new ServiceException("更新宿舍楼状态异常: " + e.getMessage());
        }
    }
} 