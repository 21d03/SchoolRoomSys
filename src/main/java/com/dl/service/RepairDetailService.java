package com.dl.service;

import com.dl.result.Result;

/**
 * 报修详情查询服务接口
 */
public interface RepairDetailService {
    
    /**
     * 根据报修单ID查询报修详情
     * @param repairId 报修单ID
     * @return 查询结果
     */
    Result getRepairDetail(String repairId);
} 