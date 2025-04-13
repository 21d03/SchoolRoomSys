package com.dl.mapper;

import com.dl.entity.vo.LeaveApprovalDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 请假审批详情查询Mapper接口
 */
@Mapper
public interface LeaveApprovalDetailMapper {
    
    /**
     * 查询请假审批详情
     * @param teacherId 教师ID
     * @param approvalId 请假审批ID
     * @param studentId 学生ID
     * @return 请假审批详情
     */
    LeaveApprovalDetailVO queryLeaveApprovalDetail(
            @Param("teacherId") String teacherId,
            @Param("approvalId") String approvalId,
            @Param("studentId") String studentId);
} 