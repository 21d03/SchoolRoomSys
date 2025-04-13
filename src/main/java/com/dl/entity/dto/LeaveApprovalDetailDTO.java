package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "请假审批详情查询参数")
public class LeaveApprovalDetailDTO {

    @ApiModelProperty(value = "教师ID", required = true)
    private String teacherId;
    
    @ApiModelProperty(value = "请假审批ID", required = true)
    private String approvalId;
    
    @ApiModelProperty(value = "学生ID", required = true)
    private String studentId;
} 