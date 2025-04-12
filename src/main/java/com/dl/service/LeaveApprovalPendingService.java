package com.dl.service;

import com.dl.entity.dto.LeaveApprovalPendingDTO;
import com.dl.entity.vo.LeaveApprovalPendingVO;
import com.dl.result.PageResult;

/**
 * 请假待审批查询服务接口
 */
public interface LeaveApprovalPendingService {
    
    /**
     * 分页查询教师待审批的请假记录
     * @param queryDTO 查询条件
     * @return 分页结果
     */
    PageResult<LeaveApprovalPendingVO> queryPendingLeaveApprovals(LeaveApprovalPendingDTO queryDTO);
} 