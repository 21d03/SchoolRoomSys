package com.dl.mapper;

import com.dl.entity.vo.BuildingRoomDistributionVO;
import com.dl.entity.vo.BuildingUsageRateVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DormResourceMapper {

    /**
     * 查询宿舍楼总数
     */
    @Select("SELECT COUNT(*) FROM room_build")
    Integer getBuildingCount();

    /**
     * 查询房间总数
     */
    @Select("SELECT sum(total_room_num) FROM room_build")
    Integer getRoomCount();

    /**
     * 查询床位总数 - 从房间表统计床位数量
     */
    @Select("SELECT SUM(room_type) FROM room_build_details")
    Integer getBedCount();

    /**
     * 查询已使用床位数 - 有学生入住的床位
     */
    @Select("SELECT COUNT(*) FROM room_info WHERE stu_id IS NOT NULL AND stu_id != ''")
    Integer getUsedBedCount();

    /**
     * 获取各宿舍楼房间分布情况
     */
    List<BuildingRoomDistributionVO> getBuildingRoomDistribution();

    /**
     * 获取宿舍楼的已使用房间数
     * @param buildId 宿舍楼ID
     * @return 已使用房间数
     */
    @Select("SELECT COUNT(DISTINCT room_id) FROM room_info WHERE build_id = #{buildId}")
    Integer getUsedRoomCount(String buildId);

    /**
     * 获取各宿舍楼使用率
     */
    List<BuildingUsageRateVO> getBuildingUsageRate();

    @Select("select build_id from room_build where build_name = #{buildingName}")
    String getBuildIdByName(String buildingName);

    @Select("select total_room_num from room_build where build_id = #{buildId}")
    Integer getTotalRoomsById(String buildId);

    /**
     * 获取不同的房间类型
     */
    @Select("SELECT DISTINCT room_type FROM room_build_details")
    List<Integer> getDistinctRoomTypes();

    /**
     * 根据房间类型获取数量
     * @param roomType 房间类型
     * @return 该类型的房间数量
     */
    @Select("SELECT COUNT(*) FROM room_build_details WHERE room_type = #{roomType}")
    Integer getRoomCountByType(Integer roomType);
}
