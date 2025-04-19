package com.dl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentIndexMapper {

    /**
     * 查询学生宿舍楼
     * @param studentId 学生ID
     * @return 宿舍楼名称
     */
    @Select("SELECT build_id FROM student_info WHERE stu_id = #{studentId}")
    String getStudentBuildName(@Param("studentId") String studentId);

    /**
     * 查询学生房间号
     * @param studentId 学生ID
     * @return 房间号
     */
    @Select("SELECT room_id FROM student_info WHERE stu_id = #{studentId}")
    String getStudentRoomId(@Param("studentId") String studentId);

    /**
     * 查询学生床位号
     * @param studentId 学生ID
     * @return 床位号
     */
    @Select("SELECT bed_no FROM room_info WHERE stu_id = #{studentId}")
    String getStudentBedId(@Param("studentId") String studentId);

    /**
     * 查询学生请假待审批数量
     * @param studentId 学生ID
     * @return 待审批数量
     */
    @Select("SELECT COUNT(*) FROM leave_approval " +
            "WHERE student_id = #{studentId} AND status = '0'")
    Integer getLeavePendingCount(@Param("studentId") String studentId);

    /**
     * 查询学生请假已处理数量
     * @param studentId 学生ID
     * @return 已处理数量
     */
    @Select("SELECT COUNT(*) FROM leave_approval " +
            "WHERE student_id = #{studentId} AND status != '0'")
    Integer getLeaveProcessedCount(@Param("studentId") String studentId);

    /**
     * 查询学生报修待审批数量
     * @param studentId 学生ID
     * @return 待审批数量
     */
    @Select("SELECT COUNT(*) FROM repair_approval " +
            "WHERE student_id = #{studentId} AND (hm_status = '0' OR (hm_status = '1' AND rp_status = '0'))")
    Integer getRepairPendingCount(@Param("studentId") String studentId);

    /**
     * 查询学生报修已完成数量
     * @param studentId 学生ID
     * @return 已完成数量
     */
    @Select("SELECT COUNT(*) FROM repair_approval " +
            "WHERE student_id = #{studentId} AND (hm_status = '2' OR (hm_status = '1' AND rp_status != '0'))")
    Integer getRepairProcessedCount(@Param("studentId") String studentId);
} 