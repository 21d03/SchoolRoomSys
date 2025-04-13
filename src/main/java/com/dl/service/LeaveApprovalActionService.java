package com.dl.service;

import com.dl.entity.dto.LeaveApprovalActionDTO;

/**
 * 请假审批操作服务接口
 */
public interface LeaveApprovalActionService {
    
    /**
     * 处理请假审批
     * @param actionDTO 审批操作参数
     * @return 是否处理成功
     */
    boolean handleApproval(LeaveApprovalActionDTO actionDTO);
} 