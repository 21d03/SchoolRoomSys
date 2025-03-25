package com.dl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.common.exception.ServiceException;
import com.dl.entity.dto.RoomBuildAddDTO;
import com.dl.entity.dto.RoomBuildQueryDTO;
import com.dl.entity.pojo.HouseMaster;
import com.dl.entity.pojo.RoomBuild;
import com.dl.entity.pojo.RoomBuildDetails;
import com.dl.entity.vo.RoomBuildVO;
import com.dl.mapper.HouseMasterMapper;
import com.dl.mapper.RoomBuildDetailsMapper;
import com.dl.mapper.RoomBuildMapper;
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
        boolean success = roomBuildMapper.insert(roomBuild) > 0;
        if (!success) {
            throw new ServiceException("保存宿舍楼信息失败");
        }

        // 更新宿管的build_id
        if (addDTO.getHmId() != null && !addDTO.getHmId().isEmpty()) {
            HouseMaster houseMaster = houseMasterMapper.selectById(addDTO.getHmId());
            if (houseMaster != null) {
                if (houseMaster.getBuildId() != null) {
                    throw new ServiceException("该宿管已分配其他宿舍楼，请选择其他宿管");
                }
                houseMaster.setBuildId(addDTO.getBuildId());
                houseMasterMapper.updateById(houseMaster);
            }
        }

        // 批量保存房间详情
        if (addDTO.getRoomDetails() != null && !addDTO.getRoomDetails().isEmpty()) {
            List<RoomBuildDetails> detailsList = addDTO.getRoomDetails().stream()
                    .map(detail -> {
                        RoomBuildDetails buildDetails = new RoomBuildDetails();
                        BeanUtils.copyProperties(detail, buildDetails);
                        buildDetails.setBuildId(addDTO.getBuildId());
                        buildDetails.setBuildName(addDTO.getBuildName());
                        return buildDetails;
                    })
                    .collect(Collectors.toList());
            
            if (!roomBuildDetailsMapper.insertBatch(detailsList)) {
                throw new ServiceException("保存房间详情失败");
            }
        }

        return true;
    }
} 