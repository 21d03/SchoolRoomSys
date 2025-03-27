package com.dl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.dto.RoomQueryDTO;
import com.dl.entity.pojo.RoomBuildDetails;
import com.dl.entity.vo.RoomVO;
import com.dl.entity.vo.RoomDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 宿舍信息详情表 Mapper 接口
 * </p>
 *
 * @author dongliang
 * @since 2024-11-01
 */
@Mapper
public interface RoomBuildDetailsMapper extends BaseMapper<RoomBuildDetails> {

    /**
     * 批量插入房间详情
     * @param detailsList 房间详情列表
     * @return 是否成功
     */
    default boolean insertBatch(@Param("list") List<RoomBuildDetails> detailsList) {
        try {
            for (RoomBuildDetails details : detailsList) {
                this.insert(details);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 分页查询房间列表
     * @param page 分页参数
     * @param queryDTO 查询条件
     * @return 分页结果
     */
    IPage<RoomVO> queryRoomPage(Page<RoomVO> page, 
                               @Param("buildId") String buildId,
                               @Param("buildName") String buildName,
                               @Param("layerNumber") String layerNumber,
                               @Param("roomId") String roomId,
                               @Param("isMixed") String isMixed,
                               @Param("roomType") String roomType,
                               @Param("status") String status,
                               @Param("collegeIds") String collegeIds,
                               @Param("manageTeacherName") String manageTeacherName);

    /**
     * 查询房间详情
     * @param buildId 宿舍楼ID
     * @param roomId 房间号
     * @return 房间详情，包含入住学生信息
     */
    RoomDetailVO getRoomDetail(@Param("buildId") String buildId, @Param("roomId") String roomId);
}
