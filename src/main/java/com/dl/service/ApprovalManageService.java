package com.dl.service;

import com.dl.entity.vo.ApprovalCountVO;

public interface ApprovalManageService {

    /**
     * 查询审批数量统计
     * @return 审批数量统计
     */
    ApprovalCountVO getApprovalCount();
}