package com.dl.service;

import com.dl.entity.dto.LeaveApplicationDTO;

public interface LeaveApplicationService {
    
    /**
     * 提交请假申请
     * @param applicationDTO 请假申请信息
     * @return 请假申请ID
     */
    String submitLeaveApplication(LeaveApplicationDTO applicationDTO);
} 