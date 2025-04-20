package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("请假申请DTO")
public class LeaveApplicationDTO {
    
    @ApiModelProperty(value = "学生ID", required = true, notes = "申请请假的学生ID")
    private String studentId;
    
    @ApiModelProperty(value = "开始时间", required = true, notes = "请假开始时间，格式：yyyy-MM-dd HH:mm:ss")
    private String startTime;
    
    @ApiModelProperty(value = "结束时间", required = true, notes = "请假结束时间，格式：yyyy-MM-dd HH:mm:ss")
    private String endTime;
    
    @ApiModelProperty(value = "请假原因", required = true, notes = "请假的具体原因")
    private String reason;
    
    @ApiModelProperty(value = "请假去向", required = true, notes = "请假期间去往的地点")
    private String destination;
    
    @ApiModelProperty(value = "紧急联系电话", required = true, notes = "请假期间的紧急联系方式")
    private String contactPhone;
} 