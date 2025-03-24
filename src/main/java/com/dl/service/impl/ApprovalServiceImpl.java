package com.dl.service.impl;

import com.dl.entity.vo.RecentApprovalVO;
import com.dl.mapper.ApprovalMapper;
import com.dl.service.ApprovalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ApprovalServiceImpl implements ApprovalService {
    
    @Resource
    private ApprovalMapper approvalMapper;
    
    @Override
    public List<RecentApprovalVO> getRecentApprovals() {
        return approvalMapper.getRecentApprovals();
    }
} 