package com.dl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 请假审批操作Mapper接口
 */
@Mapper
public interface LeaveApprovalActionMapper {
    
    /**
     * 更新请假审批状态
     * @param approvalId 请假审批ID
     * @param teacherId 教师ID
     * @param studentId 学生ID
     * @param status 审批状态
     * @param opinion 审批意见
     * @return 影响的行数
     */
    @Update("UPDATE leave_approval SET " +
            "status = #{status}, " +
            "opinion = #{opinion}, " +
            "approve_time = NOW() " +
            "WHERE id = #{approvalId} " +
            "AND teacher_id = #{teacherId} " +
            "AND student_id = #{studentId} " +
            "AND status = '0'")
    int updateApprovalStatus(
            @Param("approvalId") String approvalId,
            @Param("teacherId") String teacherId,
            @Param("studentId") String studentId,
            @Param("status") String status,
            @Param("opinion") String opinion);
} 