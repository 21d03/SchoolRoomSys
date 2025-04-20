package com.dl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.dto.LeaveRecordQueryDTO;
import com.dl.entity.vo.LeaveRecordVO;
import com.dl.mapper.LeaveRecordMapper;
import com.dl.service.LeaveRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LeaveRecordServiceImpl implements LeaveRecordService {

    @Autowired
    private LeaveRecordMapper leaveRecordMapper;
    
    @Override
    public IPage<LeaveRecordVO> queryLeaveRecordPage(LeaveRecordQueryDTO queryDTO) {
        log.info("查询学生请假记录, 查询条件: {}", queryDTO);
        
        Page<LeaveRecordVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        
        return leaveRecordMapper.queryLeaveRecordPage(
                page,
                queryDTO.getStudentId(),
                queryDTO.getStatus(),
                queryDTO.getStartTimeBegin(),
                queryDTO.getStartTimeEnd(),
                queryDTO.getReason()
        );
    }
} 