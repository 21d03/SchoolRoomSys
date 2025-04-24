package com.dl.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 宿管首页数据统计Mapper接口
 */
public interface HmIndexMapper {
    
    /**
     * 获取宿管待处理报修数
     * @param hmId 宿管ID
     * @return 待处理报修数
     */
    @Select("select count(*) from repair_approval where hm_id = #{hmId} and hm_status = '0'")
    Integer getPendingRepairCount(@Param("hmId") String hmId);
    
    /**
     * 获取宿舍楼房间总数
     * @param buildId 宿舍楼ID
     * @return 房间总数
     */
    @Select("select total_room_num from room_build where build_id = #{buildId}")
    String getTotalRoomNum(@Param("buildId") String buildId);
}