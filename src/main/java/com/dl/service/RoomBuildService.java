package com.dl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.RoomBuildAddDTO;
import com.dl.entity.dto.RoomBuildQueryDTO;
import com.dl.entity.vo.RoomBuildVO;

public interface RoomBuildService {
    
    /**
     * 分页查询宿舍楼信息
     * @param queryDTO 查询参数
     * @return 分页结果
     */
    IPage<RoomBuildVO> queryRoomBuildPage(RoomBuildQueryDTO queryDTO);
    
    /**
     * 新增宿舍楼
     * @param addDTO 新增参数
     * @return 是否成功
     */
    boolean addRoomBuild(RoomBuildAddDTO addDTO);

    /**
     * 删除宿舍楼
     * 
     * @param buildId 宿舍楼ID
     * @return 是否删除成功
     */
    boolean deleteRoomBuild(String buildId);
} 