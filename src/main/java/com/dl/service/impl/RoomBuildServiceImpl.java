package com.dl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.dto.RoomBuildQueryDTO;
import com.dl.entity.pojo.RoomBuild;
import com.dl.entity.vo.RoomBuildVO;
import com.dl.mapper.RoomBuildMapper;
import com.dl.service.IRoomBuildService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 宿舍楼信息 服务实现类
 * </p>
 *
 * @author dongliang
 * @since 2024-11-01
 */
@Service
public class RoomBuildServiceImpl extends ServiceImpl<RoomBuildMapper, RoomBuild> implements IRoomBuildService {

    @Resource
    private RoomBuildMapper roomBuildMapper;
    
    @Override
    public IPage<RoomBuildVO> queryRoomBuildPage(RoomBuildQueryDTO queryDTO) {
        Page<RoomBuildVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        return roomBuildMapper.queryRoomBuildPage(page, queryDTO.getBuildName(), queryDTO.getIsUsed());
    }
}
