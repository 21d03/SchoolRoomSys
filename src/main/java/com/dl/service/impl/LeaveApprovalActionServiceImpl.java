package com.dl.service.impl;

import com.dl.entity.dto.LeaveApprovalActionDTO;
import com.dl.mapper.LeaveApprovalActionMapper;
import com.dl.service.LeaveApprovalActionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 请假审批操作服务实现类
 */
@Service
@Slf4j
public class LeaveApprovalActionServiceImpl implements LeaveApprovalActionService {
    
    @Resource
    private LeaveApprovalActionMapper leaveApprovalActionMapper;
    
    @Override
    public boolean handleApproval(LeaveApprovalActionDTO actionDTO) {
        log.info("处理请假审批，参数：{}", actionDTO);
        
        // 检查审批状态参数是否合法
        if (!"1".equals(actionDTO.getStatus()) && !"2".equals(actionDTO.getStatus())) {
            log.error("审批状态参数错误：{}", actionDTO.getStatus());
            return false;
        }
        
        // 将空的审批意见设置为空字符串
        String opinion = actionDTO.getOpinion();
        if (opinion == null) {
            opinion = "";
        }
        
        // 更新审批状态
        int rows = leaveApprovalActionMapper.updateApprovalStatus(
                actionDTO.getApprovalId(),
                actionDTO.getTeacherId(),
                actionDTO.getStudentId(),
                actionDTO.getStatus(),
                opinion
        );
        
        return rows > 0;
    }
} 