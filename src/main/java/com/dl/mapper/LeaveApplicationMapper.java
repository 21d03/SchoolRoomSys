package com.dl.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LeaveApplicationMapper {

    /**
     * 查询当天最大的请假申请ID
     * @param prefix 前缀，格式LVyyyyMMdd
     * @return 当天最大的请假申请ID，如果没有则返回null
     */
    @Select("SELECT MAX(id) FROM leave_approval WHERE id LIKE CONCAT(#{prefix}, '%')")
    String getMaxLeaveIdByDate(@Param("prefix") String prefix);
    
    /**
     * 查询学生所属教师ID
     * @param studentId 学生ID
     * @return 教师ID
     */
    @Select("SELECT teacher_id FROM student_info WHERE stu_id = #{studentId}")
    String getTeacherIdByStudentId(@Param("studentId") String studentId);
    
    /**
     * 查询学生待审批的请假申请数量
     * @param studentId 学生ID
     * @return 待审批的请假申请数量
     */
    @Select("SELECT COUNT(*) FROM leave_approval WHERE student_id = #{studentId} AND status = '0'")
    int countPendingLeaveApplications(@Param("studentId") String studentId);
    
    /**
     * 插入请假申请记录
     * @param id 请假申请ID
     * @param studentId 学生ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param reason 请假原因
     * @param destination 请假去向
     * @param contactPhone 紧急联系电话
     * @param teacherId 教师ID
     * @param createTime 创建时间
     * @return 受影响的行数
     */
    @Insert("INSERT INTO leave_approval(id, student_id, start_time, end_time, reason, destination, contact_phone, " +
            "teacher_id, status, opinion, approve_time, create_time, update_time) " +
            "VALUES(#{id}, #{studentId}, #{startTime}, #{endTime}, #{reason}, #{destination}, #{contactPhone}, " +
            "#{teacherId}, '0', NULL, NULL, #{createTime}, #{createTime})")
    int insertLeaveApplication(@Param("id") String id, 
                              @Param("studentId") String studentId,
                              @Param("startTime") String startTime,
                              @Param("endTime") String endTime,
                              @Param("reason") String reason,
                              @Param("destination") String destination,
                              @Param("contactPhone") String contactPhone,
                              @Param("teacherId") String teacherId,
                              @Param("createTime") String createTime);
} 