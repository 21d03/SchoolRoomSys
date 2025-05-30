package com.dl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.common.exception.ServiceException;
import com.dl.entity.dto.RoomBuildAddDTO;
import com.dl.entity.dto.RoomBuildAddRoomDTO;
import com.dl.entity.dto.RoomBuildQueryDTO;
import com.dl.entity.dto.RoomBuildUpdateDTO;
import com.dl.entity.dto.RoomBuildUpdateRoomDTO;
import com.dl.entity.dto.RoomQueryDTO;
import com.dl.entity.pojo.HouseMaster;
import com.dl.entity.pojo.RoomBuild;
import com.dl.entity.pojo.RoomBuildDetails;
import com.dl.entity.pojo.StudentInfo;
import com.dl.entity.vo.RoomBuildVO;
import com.dl.entity.vo.RoomBuildStatusResultVO;
import com.dl.entity.vo.RoomVO;
import com.dl.entity.vo.RoomDetailVO;
import com.dl.entity.vo.RoomTypeVO;
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
import org.springframework.util.StringUtils;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRoomBuild(RoomBuildUpdateDTO updateDTO) {
        try {
            // 1. 更新room_build表的build_name和hm_id
            RoomBuild roomBuild = new RoomBuild();
            roomBuild.setBuildId(updateDTO.getBuildId());
            roomBuild.setBuildName(updateDTO.getBuildName());
            roomBuild.setHmId(updateDTO.getNewHmId());
            roomBuildMapper.updateById(roomBuild);
            
            // 2. 更新room_build_details表的build_name
            RoomBuildDetails updateDetails = new RoomBuildDetails();
            updateDetails.setBuildName(updateDTO.getBuildName());
            QueryWrapper<RoomBuildDetails> detailsWrapper = new QueryWrapper<>();
            detailsWrapper.eq("build_id", updateDTO.getBuildId());
            roomBuildDetailsMapper.update(updateDetails, detailsWrapper);
            
            // 3. 更新宿管表
            // 3.1 清除原宿管的build_id
            String oldHmId = houseMasterMapper.getHmId(updateDTO.getBuildId());
            houseMasterMapper.clearBuildId(oldHmId);
            
            // 3.2 设置新宿管的build_id
            HouseMaster newHouseMaster = new HouseMaster();
            newHouseMaster.setHmId(updateDTO.getNewHmId());
            newHouseMaster.setBuildId(updateDTO.getBuildId());
            houseMasterMapper.updateById(newHouseMaster);

            return true;
        } catch (Exception e) {
            log.error("更新宿舍楼信息异常", e);
            throw new ServiceException("更新宿舍楼信息异常: " + e.getMessage());
        }
    }

    @Override
    public IPage<RoomVO> queryRoomPage(RoomQueryDTO queryDTO) {
        Page<RoomVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        return roomBuildDetailsMapper.queryRoomPage(
            page,
            queryDTO.getBuildId(),
            queryDTO.getBuildName(),
            queryDTO.getLayerNumber(),
            queryDTO.getRoomId(),
            queryDTO.getIsMixed(),
            queryDTO.getRoomType(),
            queryDTO.getStatus(),
            queryDTO.getCollegeIds(),
            queryDTO.getManageTeacherName()
        );
    }

    @Override
    public RoomDetailVO getRoomDetail(String buildId, String roomId) {
        if (buildId == null || buildId.isEmpty() || roomId == null || roomId.isEmpty()) {
            log.error("查询房间详情参数错误：buildId={}, roomId={}", buildId, roomId);
            throw new ServiceException("查询房间详情参数错误");
        }
        
        return roomBuildDetailsMapper.getRoomDetail(buildId, roomId);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRoomStatus(String buildId, String roomId, String status) {
        if (buildId == null || buildId.isEmpty()) {
            log.error("更新房间状态错误：宿舍楼ID不能为空");
            throw new ServiceException("宿舍楼ID不能为空");
        }
        
        if (roomId == null || roomId.isEmpty()) {
            log.error("更新房间状态错误：房间号不能为空");
            throw new ServiceException("房间号不能为空");
        }
        
        if (status == null || status.isEmpty() || (!status.equals("0") && !status.equals("1"))) {
            log.error("更新房间状态错误：状态值不正确，必须为0或1");
            throw new ServiceException("状态值不正确，必须为0或1");
        }
        
        try {
            // 查询宿舍楼是否存在且正常使用
            QueryWrapper<RoomBuild> buildWrapper = new QueryWrapper<>();
            buildWrapper.eq("build_id", buildId).eq("is_used", "1");
            RoomBuild roomBuild = roomBuildMapper.selectOne(buildWrapper);
            
            if (roomBuild == null) {
                log.error("更新房间状态错误：宿舍楼不存在或已停用，buildId={}", buildId);
                throw new ServiceException("宿舍楼不存在或已停用");
            }
            
            // 查询房间是否存在
            QueryWrapper<RoomBuildDetails> roomWrapper = new QueryWrapper<>();
            roomWrapper.eq("build_id", buildId).eq("room_id", roomId);
            RoomBuildDetails roomDetails = roomBuildDetailsMapper.selectOne(roomWrapper);
            
            if (roomDetails == null) {
                log.error("更新房间状态错误：房间不存在，buildId={}, roomId={}", buildId, roomId);
                throw new ServiceException("房间不存在");
            }
            
            // 如果要停用房间，检查房间是否有人居住
            if (status.equals("0")) {
                QueryWrapper<StudentInfo> studentWrapper = new QueryWrapper<>();
                studentWrapper.eq("build_id", buildId).eq("room_id", roomId);
                Long studentCount = studentInfoMapper.selectCount(studentWrapper);
                
                if (studentCount > 0) {
                    log.error("更新房间状态错误：房间内还有学生居住，不能停用，buildId={}, roomId={}, studentCount={}", buildId, roomId, studentCount);
                    throw new ServiceException("房间内还有" + studentCount + "名学生居住，不能停用");
                }
            }
            
            // 更新房间状态
            RoomBuildDetails updateDetails = new RoomBuildDetails();
            updateDetails.setStatus(status);
            
            QueryWrapper<RoomBuildDetails> updateWrapper = new QueryWrapper<>();
            updateWrapper.eq("build_id", buildId).eq("room_id", roomId);
            
            int result = roomBuildDetailsMapper.update(updateDetails, updateWrapper);
            
            log.info("更新房间状态成功：buildId={}, roomId={}, status={}", buildId, roomId, status);
            return result > 0;
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("更新房间状态错误", e);
            throw new ServiceException("更新房间状态失败：" + e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addRoom(RoomBuildAddRoomDTO addRoomDTO) {
        try {
            // 1. 校验参数
            if (addRoomDTO.getBuildId() == null || addRoomDTO.getBuildId().isEmpty()) {
                throw new ServiceException("宿舍楼ID不能为空");
            }
            
            if (addRoomDTO.getRoomId() == null || addRoomDTO.getRoomId().isEmpty()) {
                throw new ServiceException("房间号不能为空");
            }
            
            // 2. 检查宿舍楼是否存在
            RoomBuild roomBuild = roomBuildMapper.selectById(addRoomDTO.getBuildId());
            if (roomBuild == null) {
                throw new ServiceException("宿舍楼不存在");
            }
            
            // 3. 检查宿舍楼状态
            if (!"1".equals(roomBuild.getIsUsed())) {
                throw new ServiceException("宿舍楼当前处于非使用状态，无法添加房间");
            }
            
            // 4. 检查房间是否已存在
            QueryWrapper<RoomBuildDetails> existCheckWrapper = new QueryWrapper<>();
            existCheckWrapper.eq("build_id", addRoomDTO.getBuildId())
                            .eq("room_id", addRoomDTO.getRoomId());
            
            Long countLong = roomBuildDetailsMapper.selectCount(existCheckWrapper);
            if (countLong > 0) {
                throw new ServiceException("该房间号已存在，请使用其他房间号");
            }
            
            // 5. 创建房间
            RoomBuildDetails roomDetails = new RoomBuildDetails();
            BeanUtils.copyProperties(addRoomDTO, roomDetails);
            roomDetails.setBuildName(roomBuild.getBuildName());
            
            // 设置默认值，确保为空的字段有默认值
            if (roomDetails.getCollegeIds() == null) {
                roomDetails.setCollegeIds("");
            }
            if (roomDetails.getManageTeacherId() == null) {
                roomDetails.setManageTeacherId("");
            }
            
            int result = roomBuildDetailsMapper.insert(roomDetails);
            
            if (result <= 0) {
                throw new ServiceException("保存房间信息失败");
            }
            
            // 6. 更新宿舍楼的房间总数
            int totalRooms = Integer.parseInt(roomBuild.getTotalRoomNum());
            
            roomBuild.setTotalRoomNum(String.valueOf(totalRooms + 1));
            
            int updateResult = roomBuildMapper.updateById(roomBuild);
            
            if (updateResult <= 0) {
                throw new ServiceException("更新宿舍楼房间总数失败");
            }
            
            log.info("新增房间成功：buildId={}, roomId={}", addRoomDTO.getBuildId(), addRoomDTO.getRoomId());
            return true;
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("新增房间错误", e);
            throw new ServiceException("新增房间失败：" + e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRoom(RoomBuildUpdateRoomDTO updateRoomDTO) {
        try {
            // 1. 校验参数
            if (updateRoomDTO.getBuildId() == null || updateRoomDTO.getBuildId().isEmpty()) {
                throw new ServiceException("宿舍楼ID不能为空");
            }
            
            if (updateRoomDTO.getRoomId() == null || updateRoomDTO.getRoomId().isEmpty()) {
                throw new ServiceException("房间号不能为空");
            }
            
            // 2. 检查宿舍楼是否存在
            RoomBuild roomBuild = roomBuildMapper.selectById(updateRoomDTO.getBuildId());
            if (roomBuild == null) {
                throw new ServiceException("宿舍楼不存在");
            }
            
            // 3. 检查宿舍楼状态
            if (!"1".equals(roomBuild.getIsUsed())) {
                throw new ServiceException("宿舍楼当前处于非使用状态，无法修改房间");
            }
            
            // 4. 检查房间是否存在
            QueryWrapper<RoomBuildDetails> roomWrapper = new QueryWrapper<>();
            roomWrapper.eq("build_id", updateRoomDTO.getBuildId())
                      .eq("room_id", updateRoomDTO.getRoomId());
            
            RoomBuildDetails roomDetails = roomBuildDetailsMapper.selectOne(roomWrapper);
            
            if (roomDetails == null) {
                throw new ServiceException("房间不存在");
            }
            
            // 5. 检查房间是否有学生居住
            QueryWrapper<StudentInfo> studentWrapper = new QueryWrapper<>();
            studentWrapper.eq("build_id", updateRoomDTO.getBuildId())
                         .eq("room_id", updateRoomDTO.getRoomId());
            
            Long studentCount = studentInfoMapper.selectCount(studentWrapper);
            
            if (studentCount > 0 && !roomDetails.getRoomType().equals(updateRoomDTO.getRoomType())) {
                throw new ServiceException("房间内已有学生居住，不能修改房间类型");
            }
            
            // 6. 更新房间信息（只更新roomType和isMixed字段）
            RoomBuildDetails updateDetails = new RoomBuildDetails();
            updateDetails.setRoomType(updateRoomDTO.getRoomType());
            updateDetails.setIsMixed(updateRoomDTO.getIsMixed());
            
            int result = roomBuildDetailsMapper.update(updateDetails, roomWrapper);
            
            if (result <= 0) {
                throw new ServiceException("修改房间信息失败");
            }
            
            log.info("修改房间成功：buildId={}, roomId={}, roomType={}, isMixed={}", 
                updateRoomDTO.getBuildId(), updateRoomDTO.getRoomId(), 
                updateRoomDTO.getRoomType(), updateRoomDTO.getIsMixed());
            
            return true;
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("修改房间错误", e);
            throw new ServiceException("修改房间失败：" + e.getMessage());
        }
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteRoom(String buildId, String roomId) {
        try {
            // 1. 校验参数
            if (buildId == null || buildId.isEmpty()) {
                throw new ServiceException("宿舍楼ID不能为空");
            }
            
            if (roomId == null || roomId.isEmpty()) {
                throw new ServiceException("房间号不能为空");
            }
            
            // 2. 检查宿舍楼是否存在
            RoomBuild roomBuild = roomBuildMapper.selectById(buildId);
            if (roomBuild == null) {
                throw new ServiceException("宿舍楼不存在");
            }
            
            // 3. 检查宿舍楼状态
            if (!"1".equals(roomBuild.getIsUsed())) {
                throw new ServiceException("宿舍楼当前处于非使用状态，无法删除房间");
            }
            
            // 4. 检查房间是否存在
            QueryWrapper<RoomBuildDetails> roomWrapper = new QueryWrapper<>();
            roomWrapper.eq("build_id", buildId)
                      .eq("room_id", roomId);
            
            RoomBuildDetails roomDetails = roomBuildDetailsMapper.selectOne(roomWrapper);
            
            if (roomDetails == null) {
                throw new ServiceException("房间不存在");
            }
            
            // 5. 检查房间是否有学生居住
            QueryWrapper<StudentInfo> studentWrapper = new QueryWrapper<>();
            studentWrapper.eq("build_id", buildId)
                         .eq("room_id", roomId);
            
            Long studentCount = studentInfoMapper.selectCount(studentWrapper);
            
            if (studentCount > 0) {
                throw new ServiceException("房间内还有" + studentCount + "名学生居住，无法删除");
            }
            
            // 6. 删除room_build_details表中的房间记录
            int deleteResult = roomBuildDetailsMapper.delete(roomWrapper);
            
            if (deleteResult <= 0) {
                throw new ServiceException("删除房间记录失败");
            }
            
            // 7. 更新宿舍楼的房间总数
            int totalRooms = Integer.parseInt(roomBuild.getTotalRoomNum());
            roomBuild.setTotalRoomNum(String.valueOf(totalRooms - 1));
            
            int updateResult = roomBuildMapper.updateById(roomBuild);
            
            if (updateResult <= 0) {
                throw new ServiceException("更新宿舍楼房间总数失败");
            }
            
            log.info("删除房间成功：buildId={}, roomId={}", buildId, roomId);
            return true;
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("删除房间错误", e);
            throw new ServiceException("删除房间失败：" + e.getMessage());
        }
    }

    @Override
    public RoomTypeVO getRoomType(String buildId, String roomId) {
        log.info("查询宿舍类型: buildId={}, roomId={}", buildId, roomId);
        
        if (buildId == null || buildId.isEmpty()) {
            throw new ServiceException("宿舍楼ID不能为空");
        }
        
        if (roomId == null || roomId.isEmpty()) {
            throw new ServiceException("房间号不能为空");
        }
        
        // 从room_build_details表查询宿舍类型
        QueryWrapper<RoomBuildDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("room_type")
                  .eq("build_id", buildId)
                  .eq("room_id", roomId);
        
        RoomBuildDetails roomDetails = roomBuildDetailsMapper.selectOne(queryWrapper);
        
        if (roomDetails == null) {
            throw new ServiceException("未找到该宿舍信息");
        }
        
        RoomTypeVO roomTypeVO = new RoomTypeVO();
        roomTypeVO.setBuildId(buildId);
        roomTypeVO.setRoomId(roomId);
        roomTypeVO.setRoomType(roomDetails.getRoomType());
        
        return roomTypeVO;
    }
} 