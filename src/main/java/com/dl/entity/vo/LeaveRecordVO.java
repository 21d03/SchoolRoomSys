package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("请假记录VO")
public class LeaveRecordVO {
    
    @ApiModelProperty(value = "请假申请ID")
    private String id;
    
    @ApiModelProperty(value = "学生ID")
    private String studentId;
    
    @ApiModelProperty(value = "开始时间", notes = "格式：yyyy-MM-dd HH:mm:ss")
    private String startTime;
    
    @ApiModelProperty(value = "结束时间", notes = "格式：yyyy-MM-dd HH:mm:ss")
    private String endTime;
    
    @ApiModelProperty(value = "请假原因")
    private String reason;
    
    @ApiModelProperty(value = "请假去向")
    private String destination;
    
    @ApiModelProperty(value = "紧急联系电话")
    private String contactPhone;
    
    @ApiModelProperty(value = "教师ID")
    private String teacherId;
    
    @ApiModelProperty(value = "审批状态", notes = "0-待审批，1-已通过，2-已驳回")
    private String status;
    
    @ApiModelProperty(value = "审批意见")
    private String opinion;
    
    @ApiModelProperty(value = "审批时间", notes = "格式：yyyy-MM-dd HH:mm:ss")
    private String approveTime;
    
    @ApiModelProperty(value = "创建时间", notes = "格式：yyyy-MM-dd HH:mm:ss")
    private String createTime;
    
    @ApiModelProperty(value = "更新时间", notes = "格式：yyyy-MM-dd HH:mm:ss")
    private String updateTime;
} 