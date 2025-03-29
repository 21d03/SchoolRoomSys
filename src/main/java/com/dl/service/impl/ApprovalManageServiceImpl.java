package com.dl.service.impl;

import com.dl.entity.dto.LeaveApprovalQueryDTO;
import com.dl.entity.dto.RepairApprovalQueryDTO;
import com.dl.entity.vo.ApprovalCountVO;
import com.dl.entity.vo.LeaveApprovalVO;
import com.dl.entity.vo.RepairApprovalVO;
import com.dl.mapper.ApprovalManageMapper;
import com.dl.service.ApprovalManageService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @Override
    public IPage<LeaveApprovalVO> queryLeaveApprovalPage(LeaveApprovalQueryDTO queryDTO) {
        Page<LeaveApprovalVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        return approvalManageMapper.queryLeaveApprovalPage(
            page,
            queryDTO.getStudentId(),
            queryDTO.getStudentName(),
            queryDTO.getStatus()
        );
    }

    @Override
    public IPage<RepairApprovalVO> queryRepairApprovalPage(RepairApprovalQueryDTO queryDTO) {
        Page<RepairApprovalVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        return approvalManageMapper.queryRepairApprovalPage(
            page,
            queryDTO.getStudentId(),
            queryDTO.getStudentName(),
            queryDTO.getHmStatus(),
            queryDTO.getRpStatus()
        );
    }
}