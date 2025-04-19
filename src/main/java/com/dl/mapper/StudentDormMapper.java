package com.dl.mapper;

import com.dl.entity.vo.StudentDormVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface StudentDormMapper {

    /**
     * 查询学生宿舍基本信息
     * @param studentId 学生ID
     * @return 宿舍基本信息
     */
    @Select("SELECT build_id, room_id, bed_no FROM room_info WHERE stu_id = #{studentId}")
    StudentDormVO getStudentDormInfo(@Param("studentId") String studentId);

    /**
     * 查询宿管信息
     * @param buildId 宿舍楼ID
     * @return 宿管信息
     */
    @Select("SELECT hm_name, hm_phone FROM house_master WHERE build_id = #{buildId}")
    StudentDormVO getHouseMasterInfo(@Param("buildId") String buildId);

    /**
     * 查询室友信息
     * @param buildId 宿舍楼ID
     * @param roomId 房间号
     * @param studentId 当前学生ID（排除自己）
     * @return 室友信息列表
     */
    @Select("SELECT " +
            "ri.stu_id as studentId, " +
            "si.stu_name as stuName, " +
            "ri.bed_no as bedNo, " +
            "CONCAT(si.profession, si.class_room) as className, " +
            "su.phone " +
            "FROM room_info ri " +
            "LEFT JOIN student_info si ON ri.stu_id = si.stu_id " +
            "LEFT JOIN student_user su ON ri.stu_id = su.user_id " +
            "WHERE ri.build_id = #{buildId} " +
            "AND ri.room_id = #{roomId} " +
            "AND ri.stu_id != #{studentId}")
    List<StudentDormVO.RoommateVO> getRoommateInfo(@Param("buildId") String buildId,
                                                   @Param("roomId") String roomId,
                                                   @Param("studentId") String studentId);
} 