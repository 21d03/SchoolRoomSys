package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "请假待审批记录")
public class LeaveApprovalPendingVO {

    @ApiModelProperty(value = "请假ID")
    private String leaveId;
    
    @ApiModelProperty(value = "学生ID")
    private String studentId;
    
    @ApiModelProperty(value = "学生姓名")
    private String studentName;
    
    @ApiModelProperty(value = "请假开始时间")
    private String startTime;
    
    @ApiModelProperty(value = "请假结束时间")
    private String endTime;
    
    @ApiModelProperty(value = "请假天数")
    private Integer days;
    
    @ApiModelProperty(value = "请假原因")
    private String reason;
    
    @ApiModelProperty(value = "请假去向")
    private String destination;
    
    @ApiModelProperty(value = "紧急联系电话")
    private String contactPhone;
    
    @ApiModelProperty(value = "审批状态（0-待审批，1-已通过，2-已拒绝）")
    private String status;
    
    @ApiModelProperty(value = "提交时间")
    private String submitTime;
} 