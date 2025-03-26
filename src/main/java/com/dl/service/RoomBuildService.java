package com.dl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.RoomBuildAddDTO;
import com.dl.entity.dto.RoomBuildQueryDTO;
import com.dl.entity.dto.RoomBuildUpdateDTO;
import com.dl.entity.vo.RoomBuildVO;
import com.dl.entity.vo.RoomBuildStatusResultVO;

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

    /**
     * 更新宿舍楼状态
     * 
     * @param buildId 宿舍楼ID
     * @param isUsed 使用状态 1使用 0暂停使用
     * @return 更新结果，包含是否成功及失败原因
     */
    RoomBuildStatusResultVO updateRoomBuildStatus(String buildId, String isUsed);

    /**
     * 更新宿舍楼信息
     * 
     * @param updateDTO 更新参数
     * @return 是否更新成功
     */
    boolean updateRoomBuild(RoomBuildUpdateDTO updateDTO);
} 