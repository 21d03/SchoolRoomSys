package com.dl.mapper;

import com.dl.entity.vo.RoomQueryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoomQueryMapper {
    
    /**
     * 根据宿舍楼ID和宿舍号查询宿舍信息
     * @param buildId 宿舍楼ID
     * @param roomId 宿舍号，可为空
     * @return 宿舍信息列表
     */
    @Select("<script>" +
            "SELECT rbd.room_id, rbd.room_type, " +
            "(SELECT COUNT(1) FROM room_info ri WHERE ri.build_id = rbd.build_id AND ri.room_id = rbd.room_id) AS occupied_count " +
            "FROM room_build_details rbd " +
            "WHERE rbd.build_id = #{buildId} " +
            "<if test='roomId != null and roomId != \"\"'> AND rbd.room_id = #{roomId} </if> " +
            "ORDER BY rbd.room_id " +
            "</script>")
    List<RoomQueryVO> queryRooms(@Param("buildId") String buildId, @Param("roomId") String roomId);
} 