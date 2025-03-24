package com.dl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.RoomBuildQueryDTO;
import com.dl.entity.vo.RoomBuildVO;

public interface RoomBuildService {
    
    /**
     * 分页查询宿舍楼信息
     * @param queryDTO 查询参数
     * @return 分页结果
     */
    IPage<RoomBuildVO> queryRoomBuildPage(RoomBuildQueryDTO queryDTO);
} 