package com.dl.service.impl;

import com.dl.entity.vo.RepairApprovalVO;
import com.dl.mapper.RepairApprovalQueryMapper;
import com.dl.result.Result;
import com.dl.service.RepairApprovalQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报修单查询服务实现类
 */
@Service
@Slf4j
public class RepairApprovalQueryServiceImpl implements RepairApprovalQueryService {
    
    @Autowired
    private RepairApprovalQueryMapper repairApprovalQueryMapper;
    
    @Override
    public Result getRepairApprovalList(String hmId) {
        log.info("查询宿管[{}]的报修单列表", hmId);
        
        try {
            List<RepairApprovalVO> repairApprovals = repairApprovalQueryMapper.getRepairApprovalList(hmId);
            return Result.success(repairApprovals);
        } catch (Exception e) {
            log.error("查询报修单列表失败", e);
            return Result.error("查询报修单列表失败");
        }
    }
} 