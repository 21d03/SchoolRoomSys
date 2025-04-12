package com.dl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.vo.LeaveApprovalPendingVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 请假待审批查询Mapper接口
 */
@Mapper
public interface LeaveApprovalPendingMapper {
    
    /**
     * 分页查询教师待审批的请假记录
     * @param page 分页参数
     * @param teacherId 教师ID
     * @param studentId 学生ID（可选，支持模糊查询）
     * @param studentName 学生姓名（可选，支持模糊查询）
     * @param status 审批状态（可选）
     * @return 分页结果
     */
    IPage<LeaveApprovalPendingVO> queryPendingLeaveApprovals(
            Page<LeaveApprovalPendingVO> page,
            @Param("teacherId") String teacherId,
            @Param("studentId") String studentId,
            @Param("studentName") String studentName,
            @Param("status") String status);
} 