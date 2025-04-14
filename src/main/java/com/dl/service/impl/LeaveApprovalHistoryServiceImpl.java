package com.dl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.dto.LeaveApprovalHistoryDTO;
import com.dl.entity.vo.LeaveApprovalHistoryVO;
import com.dl.mapper.LeaveApprovalHistoryMapper;
import com.dl.result.PageResult;
import com.dl.service.LeaveApprovalHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 请假审批历史查询服务实现类
 */
@Service
@Slf4j
public class LeaveApprovalHistoryServiceImpl implements LeaveApprovalHistoryService {
    
    @Resource
    private LeaveApprovalHistoryMapper leaveApprovalHistoryMapper;
    
    @Override
    public PageResult<LeaveApprovalHistoryVO> queryApprovalHistory(LeaveApprovalHistoryDTO queryDTO) {
        log.info("查询教师[{}]的请假审批历史记录，参数：{}", queryDTO.getTeacherId(), queryDTO);
        
        // 创建分页对象
        Page<LeaveApprovalHistoryVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        
        // 查询数据
        IPage<LeaveApprovalHistoryVO> pageResult = leaveApprovalHistoryMapper.queryApprovalHistory(
                page,
                queryDTO.getTeacherId(),
                queryDTO.getStudentId(),
                queryDTO.getStudentName(),
                queryDTO.getStatus(),
                queryDTO.getSubmitStartDate(),
                queryDTO.getSubmitEndDate()
        );
        
        // 封装结果
        return new PageResult<>(pageResult.getTotal(), pageResult.getRecords());
    }
} 