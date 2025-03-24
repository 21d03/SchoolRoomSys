package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "最近审批记录")
public class RecentApprovalVO {
    
    @ApiModelProperty("审批单编号")
    private String approvalId;
    
    @ApiModelProperty("审批类型：1-请假，2-维修")
    private String approvalType;
    
    @ApiModelProperty("申请人学号")
    private String studentId;
    
    @ApiModelProperty("申请人姓名")
    private String studentName;
    
    @ApiModelProperty("申请内容")
    private String content;
    
    @ApiModelProperty("状态：0-未审批，1-已审批，2-拒绝")
    private String status;
    
    @ApiModelProperty("申请时间")
    private LocalDateTime createTime;
} 