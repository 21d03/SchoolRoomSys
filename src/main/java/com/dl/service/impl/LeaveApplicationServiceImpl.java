package com.dl.service.impl;

import com.dl.entity.dto.LeaveApplicationDTO;
import com.dl.mapper.LeaveApplicationMapper;
import com.dl.service.LeaveApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class LeaveApplicationServiceImpl implements LeaveApplicationService {

    @Autowired
    private LeaveApplicationMapper leaveApplicationMapper;
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public String submitLeaveApplication(LeaveApplicationDTO applicationDTO) {
        log.info("提交请假申请, 申请信息: {}", applicationDTO);
        
        // 检查学生是否已有待审批的请假申请
        int pendingCount = leaveApplicationMapper.countPendingLeaveApplications(applicationDTO.getStudentId());
        if (pendingCount > 0) {
            log.warn("学生已有待审批的请假申请, 不能重复提交, 学生ID: {}", applicationDTO.getStudentId());
            throw new RuntimeException("您已有待审批的请假申请，请等待审批完成后再提交新的申请");
        }
        
        // 生成请假申请ID
        String leaveId = generateLeaveId();
        log.info("生成请假申请ID: {}", leaveId);
        
        // 查询学生所属教师ID
        String teacherId = leaveApplicationMapper.getTeacherIdByStudentId(applicationDTO.getStudentId());
        if (teacherId == null || teacherId.isEmpty()) {
            log.error("未找到学生所属教师信息, 学生ID: {}", applicationDTO.getStudentId());
            throw new RuntimeException("未找到学生所属教师信息");
        }
        
        // 获取当前时间作为创建时间
        String createTime = LocalDateTime.now().format(DATETIME_FORMATTER);
        
        // 插入请假申请记录
        int result = leaveApplicationMapper.insertLeaveApplication(
                leaveId,
                applicationDTO.getStudentId(),
                applicationDTO.getStartTime(),
                applicationDTO.getEndTime(),
                applicationDTO.getReason(),
                applicationDTO.getDestination(),
                applicationDTO.getContactPhone(),
                teacherId,
                createTime
        );
        
        if (result > 0) {
            log.info("请假申请提交成功, ID: {}", leaveId);
            return leaveId;
        } else {
            log.error("请假申请提交失败");
            throw new RuntimeException("请假申请提交失败");
        }
    }
    
    /**
     * 生成请假申请ID
     * 格式：LVyyyyMMddxxx
     * @return 请假申请ID
     */
    private String generateLeaveId() {
        // 获取当前日期作为前缀
        String datePrefix = "LV" + LocalDateTime.now().format(DATE_FORMATTER);
        
        // 查询当天最大的请假申请ID
        String maxId = leaveApplicationMapper.getMaxLeaveIdByDate(datePrefix);
        
        // 如果当天没有请假申请，则序号从001开始
        if (maxId == null || maxId.isEmpty()) {
            return datePrefix + "001";
        }
        
        // 否则，序号+1
        int sequence = Integer.parseInt(maxId.substring(maxId.length() - 3)) + 1;
        return String.format("%s%03d", datePrefix, sequence);
    }
} 