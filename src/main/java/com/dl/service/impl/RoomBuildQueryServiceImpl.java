package com.dl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.dto.RoomBuildQueryDTO;
import com.dl.entity.vo.RoomBuildVO;
import com.dl.mapper.RoomBuildMapper;
import com.dl.service.RoomBuildService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoomBuildQueryServiceImpl implements RoomBuildService {
    
    @Resource
    private RoomBuildMapper roomBuildMapper;
    
    @Override
    public IPage<RoomBuildVO> queryRoomBuildPage(RoomBuildQueryDTO queryDTO) {
        Page<RoomBuildVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        return roomBuildMapper.queryRoomBuildPage(page, queryDTO.getBuildName(), queryDTO.getIsUsed());
    }
} 