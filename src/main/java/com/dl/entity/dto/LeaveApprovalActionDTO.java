package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "请假审批操作参数")
public class LeaveApprovalActionDTO {

    @ApiModelProperty(value = "教师ID", required = true)
    private String teacherId;
    
    @ApiModelProperty(value = "请假审批ID", required = true)
    private String approvalId;
    
    @ApiModelProperty(value = "学生ID", required = true)
    private String studentId;
    
    @ApiModelProperty(value = "审批状态（1-通过，2-拒绝）", required = true)
    private String status;
    
    @ApiModelProperty(value = "审批意见")
    private String opinion;
} 