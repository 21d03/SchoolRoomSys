package com.dl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
