package com.dl.service;

import com.dl.entity.vo.ApprovalCountVO;
import com.dl.entity.dto.LeaveApprovalQueryDTO;
import com.dl.entity.vo.LeaveApprovalVO;
import com.dl.entity.dto.RepairApprovalQueryDTO;
import com.dl.entity.vo.RepairApprovalVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface ApprovalManageService {

    /**
     * 查询审批数量统计
     * @return 审批数量统计
     */
    ApprovalCountVO getApprovalCount();

    /**
     * 分页查询请假审批列表
     * @param queryDTO 查询条件
     * @return 请假审批列表
     */
    IPage<LeaveApprovalVO> queryLeaveApprovalPage(LeaveApprovalQueryDTO queryDTO);

    /**
     * 分页查询报修审批列表
     * @param queryDTO 查询条件
     * @return 报修审批列表
     */
    IPage<RepairApprovalVO> queryRepairApprovalPage(RepairApprovalQueryDTO queryDTO);
}