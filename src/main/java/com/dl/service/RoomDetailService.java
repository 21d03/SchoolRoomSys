package com.dl.service;

import com.dl.entity.dto.RoomDetailQueryDTO;
import com.dl.result.Result;

/**
 * 宿舍详情查询服务接口
 */
public interface RoomDetailService {
    
    /**
     * 查询宿舍详细信息
     * @param dto 查询参数
     * @return 查询结果
     */
    Result getRoomDetail(RoomDetailQueryDTO dto);
} 