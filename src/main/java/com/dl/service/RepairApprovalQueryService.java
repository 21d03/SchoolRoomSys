package com.dl.service;

import com.dl.result.Result;

/**
 * 报修单查询服务接口
 */
public interface RepairApprovalQueryService {
    
    /**
     * 查询宿管用户的报修单列表
     * @param hmId 宿管ID
     * @return 查询结果
     */
    Result getRepairApprovalList(String hmId);
} 