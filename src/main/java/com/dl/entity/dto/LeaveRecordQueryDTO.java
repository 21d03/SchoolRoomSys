package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("请假记录查询DTO")
public class LeaveRecordQueryDTO {
    
    @ApiModelProperty(value = "学生ID", required = true, notes = "查询的学生ID")
    private String studentId;
    
    @ApiModelProperty(value = "页码", example = "1")
    private Integer pageNum = 1;
    
    @ApiModelProperty(value = "每页数量", example = "10")
    private Integer pageSize = 10;
    
    @ApiModelProperty(value = "审批状态", notes = "0-待审批，1-已通过，2-已驳回")
    private String status;
    
    @ApiModelProperty(value = "开始时间范围(起始)", notes = "格式：yyyy-MM-dd HH:mm:ss")
    private String startTimeBegin;
    
    @ApiModelProperty(value = "开始时间范围(结束)", notes = "格式：yyyy-MM-dd HH:mm:ss")
    private String startTimeEnd;
    
    @ApiModelProperty(value = "请假原因", notes = "支持模糊查询")
    private String reason;
} 