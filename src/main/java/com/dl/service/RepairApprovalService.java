package com.dl.service;

import com.dl.result.Result;

public interface RepairApprovalService {
    /**
     * 处理报修审批
     * @param repairId 报修单ID
     * @param status 审批状态(1:通过,2:拒绝)
     * @param opinion 审批意见
     * @param buildId 宿舍楼ID
     * @return 处理结果
     */
    Result handleApproval(String repairId, Integer status, String opinion, String buildId);
} 