package com.dl.service;

import com.dl.entity.dto.RoomQueryDTO;
import com.dl.result.Result;

/**
 * 宿舍查询服务接口
 */
public interface RoomQueryService {
    
    /**
     * 查询宿舍信息
     * @param dto 查询参数
     * @return 查询结果
     */
    Result queryRooms(RoomQueryDTO dto);
} 