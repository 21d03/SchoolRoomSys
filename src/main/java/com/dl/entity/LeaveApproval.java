package com.dl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("leave_approval")
public class LeaveApproval {
    
    /**
     * 主键ID
     */
    private String id;
    
    /**
     * 申请学生ID
     */
    private String studentId;
    
    /**
     * 请假开始时间
     */
    private LocalDateTime startTime;
    
    /**
     * 请假结束时间
     */
    private LocalDateTime endTime;
    
    /**
     * 请假原因
     */
    private String reason;
    
    /**
     * 请假去向
     */
    private String destination;
    
    /**
     * 紧急联系电话
     */
    private String contactPhone;
    
    /**
     * 辅导员ID
     */
    private String teacherId;
    
    /**
     * 审批状态 0-待审批 1-已通过 2-已驳回
     */
    private String status;
    
    /**
     * 审批意见
     */
    private String opinion;
    
    /**
     * 审批时间
     */
    private LocalDateTime approveTime;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
} 