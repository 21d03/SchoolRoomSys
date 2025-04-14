package com.dl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.vo.LeaveApprovalHistoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 请假审批历史查询Mapper接口
 */
@Mapper
public interface LeaveApprovalHistoryMapper {
    
    /**
     * 分页查询教师已审批的请假记录
     * @param page 分页参数
     * @param teacherId 教师ID
     * @param studentId 学生ID（可选，支持模糊查询）
     * @param studentName 学生姓名（可选，支持模糊查询）
     * @param status 审批状态（可选，1-已通过，2-已拒绝）
     * @param submitStartDate 提交开始日期（可选，格式：yyyy-MM-dd）
     * @param submitEndDate 提交结束日期（可选，格式：yyyy-MM-dd）
     * @return 分页结果
     */
    IPage<LeaveApprovalHistoryVO> queryApprovalHistory(
            Page<LeaveApprovalHistoryVO> page,
            @Param("teacherId") String teacherId,
            @Param("studentId") String studentId,
            @Param("studentName") String studentName,
            @Param("status") String status,
            @Param("submitStartDate") String submitStartDate,
            @Param("submitEndDate") String submitEndDate);
} 