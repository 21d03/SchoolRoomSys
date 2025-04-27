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
@ApiModel(description = "报修单查询结果")
public class RepairApprovalVO {
    
    @ApiModelProperty("报修单号")
    private String id;
    
    @ApiModelProperty("学生ID")
    private String studentId;
    
    @ApiModelProperty("学生姓名")
    private String studentName;
    
    @ApiModelProperty("报修描述，来自repair_approval表的description字段")
    private String content;
    
    @ApiModelProperty("报修时间")
    private LocalDateTime createTime;
    
    @ApiModelProperty("宿管审批状态：0-待审批 1-已通过 2-已拒绝")
    private String hmStatus;
    
    @ApiModelProperty("维修人员审批状态：0-待审批 1-已完成 2-拒绝")
    private String rpStatus;
    
    @ApiModelProperty("当前审批人ID")
    private String approverId;
    
    @ApiModelProperty("当前审批人姓名")
    private String approverName;
    
    @ApiModelProperty("是否有催单")
    private Boolean hasUrge;
}
