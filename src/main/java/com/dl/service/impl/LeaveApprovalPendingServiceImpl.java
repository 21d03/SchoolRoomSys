package com.dl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.dto.LeaveApprovalPendingDTO;
import com.dl.entity.vo.LeaveApprovalPendingVO;
import com.dl.mapper.LeaveApprovalPendingMapper;
import com.dl.result.PageResult;
import com.dl.service.LeaveApprovalPendingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 请假待审批查询服务实现类
 */
@Service
@Slf4j
public class LeaveApprovalPendingServiceImpl implements LeaveApprovalPendingService {
    
    @Resource
    private LeaveApprovalPendingMapper leaveApprovalPendingMapper;
    
    @Override
    public PageResult<LeaveApprovalPendingVO> queryPendingLeaveApprovals(LeaveApprovalPendingDTO queryDTO) {
        log.info("查询教师[{}]请假审批记录，参数：{}", queryDTO.getTeacherId(), queryDTO);
        
        // 创建分页对象
        Page<LeaveApprovalPendingVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        
        // 查询数据
        IPage<LeaveApprovalPendingVO> pageResult = leaveApprovalPendingMapper.queryPendingLeaveApprovals(
                page,
                queryDTO.getTeacherId(),
                queryDTO.getStudentId(),
                queryDTO.getStudentName(),
                queryDTO.getStatus()
        );
        
        // 封装结果
        return new PageResult<>(pageResult.getTotal(), pageResult.getRecords());
    }
} 