package com.dl.service;

import com.dl.entity.vo.RecentApprovalVO;

import java.util.List;

public interface ApprovalService {
    
    /**
     * 获取最近的审批记录
     * @return 最近10条审批记录
     */
    List<RecentApprovalVO> getRecentApprovals();
} 