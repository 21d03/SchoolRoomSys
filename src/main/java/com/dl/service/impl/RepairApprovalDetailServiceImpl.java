package com.dl.service.impl;

import com.dl.entity.vo.RepairApprovalDetailVO;
import com.dl.mapper.RepairApprovalDetailMapper;
import com.dl.service.RepairApprovalDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RepairApprovalDetailServiceImpl implements RepairApprovalDetailService {

    @Autowired
    private RepairApprovalDetailMapper repairApprovalDetailMapper;
    
    @Override
    public RepairApprovalDetailVO getRepairApprovalDetail(String approvalId) {
        log.info("查询报修审批单详情, ID: {}", approvalId);
        return repairApprovalDetailMapper.getRepairApprovalDetail(approvalId);
    }
} 