package com.dl.service.impl;

import com.dl.entity.dto.LeaveApprovalDetailDTO;
import com.dl.entity.vo.LeaveApprovalDetailVO;
import com.dl.mapper.LeaveApprovalDetailMapper;
import com.dl.service.LeaveApprovalDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 请假审批详情查询服务实现类
 */
@Service
@Slf4j
public class LeaveApprovalDetailServiceImpl implements LeaveApprovalDetailService {
    
    @Resource
    private LeaveApprovalDetailMapper leaveApprovalDetailMapper;
    
    @Override
    public LeaveApprovalDetailVO queryLeaveApprovalDetail(LeaveApprovalDetailDTO queryDTO) {
        log.info("查询请假审批详情，参数：{}", queryDTO);
        
        // 查询数据
        LeaveApprovalDetailVO result = leaveApprovalDetailMapper.queryLeaveApprovalDetail(
                queryDTO.getTeacherId(),
                queryDTO.getApprovalId(),
                queryDTO.getStudentId()
        );
        
        return result;
    }
} 