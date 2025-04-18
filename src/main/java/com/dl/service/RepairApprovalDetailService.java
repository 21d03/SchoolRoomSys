package com.dl.service;

import com.dl.entity.vo.RepairApprovalDetailVO;

public interface RepairApprovalDetailService {
    
    /**
     * 根据ID查询报修审批单详情
     * @param approvalId 报修审批单ID
     * @return 报修审批单详情
     */
    RepairApprovalDetailVO getRepairApprovalDetail(String approvalId);
} 