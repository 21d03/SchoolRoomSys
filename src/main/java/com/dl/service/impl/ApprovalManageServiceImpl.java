package com.dl.service.impl;

import com.dl.entity.vo.ApprovalCountVO;
import com.dl.mapper.ApprovalManageMapper;
import com.dl.service.ApprovalManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApprovalManageServiceImpl implements ApprovalManageService {

    @Autowired
    private ApprovalManageMapper approvalManageMapper;

    @Override
    public ApprovalCountVO getApprovalCount() {
        ApprovalCountVO countVO = new ApprovalCountVO();
        
        // 获取请假审批数量
        Integer leaveTotalCount = approvalManageMapper.getLeaveApprovalTotalCount();
        Integer leavePendingCount = approvalManageMapper.getLeaveApprovalPendingCount();
        Integer leaveApprovedCount = approvalManageMapper.getLeaveApprovalApprovedCount();
        Integer leaveRejectedCount = approvalManageMapper.getLeaveApprovalRejectedCount();
        
        // 获取报修审批数量
        Integer repairTotalCount = approvalManageMapper.getRepairApprovalTotalCount();
        Integer repairPendingCount = approvalManageMapper.getRepairApprovalPendingCount();
        Integer repairApprovedCount = approvalManageMapper.getRepairApprovalApprovedCount();
        Integer repairRejectedCount = approvalManageMapper.getRepairApprovalRejectedCount();
        
        // 合并请假和报修审批数量
        countVO.setTotalCount(leaveTotalCount + repairTotalCount);
        countVO.setPendingCount(leavePendingCount + repairPendingCount);
        countVO.setApprovedCount(leaveApprovedCount + repairApprovedCount);
        countVO.setRejectedCount(leaveRejectedCount + repairRejectedCount);
        
        return countVO;
    }
}