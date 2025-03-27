package com.dl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.RoomBuildAddDTO;
import com.dl.entity.dto.RoomBuildAddRoomDTO;
import com.dl.entity.dto.RoomBuildQueryDTO;
import com.dl.entity.dto.RoomBuildUpdateDTO;
import com.dl.entity.dto.RoomBuildUpdateRoomDTO;
import com.dl.entity.dto.RoomQueryDTO;
import com.dl.entity.vo.RoomBuildVO;
import com.dl.entity.vo.RoomBuildStatusResultVO;
import com.dl.entity.vo.RoomVO;
import com.dl.entity.vo.RoomDetailVO;

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

    /**
     * 分页查询房间列表
     * @param queryDTO 查询条件
     * @return 分页结果
     */
    IPage<RoomVO> queryRoomPage(RoomQueryDTO queryDTO);

    /**
     * 查询房间详情
     * @param buildId 宿舍楼ID
     * @param roomId 房间号
     * @return 房间详情，包含入住学生信息
     */
    RoomDetailVO getRoomDetail(String buildId, String roomId);
    
    /**
     * 更新房间使用状态
     * @param buildId 宿舍楼ID
     * @param roomId 房间号
     * @param status 使用状态：1-正常使用，0-暂停使用
     * @return 是否更新成功
     */
    boolean updateRoomStatus(String buildId, String roomId, String status);
    
    /**
     * 新增房间
     * @param addRoomDTO 新增房间参数
     * @return 是否新增成功
     */
    boolean addRoom(RoomBuildAddRoomDTO addRoomDTO);

    /**
     * 修改房间信息
     * @param updateRoomDTO 修改房间参数
     * @return 是否修改成功
     */
    boolean updateRoom(RoomBuildUpdateRoomDTO updateRoomDTO);
} 