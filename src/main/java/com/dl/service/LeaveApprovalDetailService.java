package com.dl.service;

import com.dl.entity.dto.LeaveApprovalDetailDTO;
import com.dl.entity.vo.LeaveApprovalDetailVO;

/**
 * 请假审批详情查询服务接口
 */
public interface LeaveApprovalDetailService {
    
    /**
     * 查询请假审批详情
     * @param queryDTO 查询参数
     * @return 请假审批详情
     */
    LeaveApprovalDetailVO queryLeaveApprovalDetail(LeaveApprovalDetailDTO queryDTO);
} 