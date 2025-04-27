package com.dl.mapper;

import com.dl.entity.vo.RoomDetailVO;
import com.dl.entity.vo.StudentRoomDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoomDetailMapper {
    
    /**
     * 查询宿舍基本信息
     * @param buildId 宿舍楼ID
     * @param roomId 宿舍号
     * @return 宿舍基本信息
     */
    @Select("SELECT rbd.room_id, rbd.build_id, rbd.room_type, " +
            "(SELECT COUNT(1) FROM room_info ri WHERE ri.build_id = rbd.build_id AND ri.room_id = rbd.room_id) AS occupied_count " +
            "FROM room_build_details rbd " +
            "WHERE rbd.build_id = #{buildId} AND rbd.room_id = #{roomId}")
    RoomDetailVO getRoomDetail(@Param("buildId") String buildId, @Param("roomId") String roomId);
    
    /**
     * 查询宿舍学生详细信息
     * @param buildId 宿舍楼ID
     * @param roomId 宿舍号
     * @return 学生详细信息列表
     */
    @Select("SELECT " +
            "ri.stu_id, ri.stu_name, ri.bed_no, " +
            "CONCAT(si.college, ' | ', si.profession, ' | ', si.class_room) AS major_class, " +
            "su.phone, " +
            "si.teacher_name, si.teacher_id, " +
            "(SELECT phone FROM school_user WHERE user_id = si.teacher_id) AS teacher_phone " +
            "FROM room_info ri " +
            "LEFT JOIN student_info si ON ri.stu_id = si.stu_id " +
            "LEFT JOIN student_user su ON ri.stu_id = su.user_id " +
            "WHERE ri.build_id = #{buildId} AND ri.room_id = #{roomId} " +
            "ORDER BY ri.bed_no")
    List<StudentRoomDetailVO> getStudentsInRoom(@Param("buildId") String buildId, @Param("roomId") String roomId);
} 