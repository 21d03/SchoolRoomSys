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
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * 请假审批历史查询服务实现类
 */
@Service
@Slf4j
public class LeaveApprovalHistoryServiceImpl implements LeaveApprovalHistoryService {
    
    @Resource
    private LeaveApprovalHistoryMapper leaveApprovalHistoryMapper;
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    @Override
    public PageResult<LeaveApprovalHistoryVO> queryApprovalHistory(LeaveApprovalHistoryDTO queryDTO) {
        log.info("查询教师[{}]的请假审批历史记录，参数：{}", queryDTO.getTeacherId(), queryDTO);
        
        // 处理日期格式
        String startDate = null;
        String endDate = null;
        
        try {
            // 格式化开始日期
            if (StringUtils.hasText(queryDTO.getSubmitStartDate())) {
                LocalDate date = LocalDate.parse(queryDTO.getSubmitStartDate(), FORMATTER);
                startDate = date.format(FORMATTER);
                log.info("格式化后的审批开始日期: {}", startDate);
            }
            
            // 格式化结束日期
            if (StringUtils.hasText(queryDTO.getSubmitEndDate())) {
                LocalDate date = LocalDate.parse(queryDTO.getSubmitEndDate(), FORMATTER);
                endDate = date.format(FORMATTER);
                log.info("格式化后的审批结束日期: {}", endDate);
            }
        } catch (DateTimeParseException e) {
            log.error("日期格式解析错误: {}", e.getMessage());
            // 日期格式错误时，使用原始值
            startDate = queryDTO.getSubmitStartDate();
            endDate = queryDTO.getSubmitEndDate();
        }
        
        // 创建分页对象
        Page<LeaveApprovalHistoryVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        
        // 查询数据
        IPage<LeaveApprovalHistoryVO> pageResult = leaveApprovalHistoryMapper.queryApprovalHistory(
                page,
                queryDTO.getTeacherId(),
                queryDTO.getStudentId(),
                queryDTO.getStudentName(),
                queryDTO.getStatus(),
                startDate,
                endDate
        );
        
        log.info("查询结果 - 总记录数: {}", pageResult.getTotal());
        
        // 封装结果
        return new PageResult<>(pageResult.getTotal(), pageResult.getRecords());
    }
} 