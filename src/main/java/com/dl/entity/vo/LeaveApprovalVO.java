package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("请假审批VO")
public class LeaveApprovalVO {

    @ApiModelProperty(value = "审批ID")
    private String approvalId;

    @ApiModelProperty(value = "学生ID")
    private String studentId;

    @ApiModelProperty(value = "学生姓名")
    private String studentName;

    @ApiModelProperty(value = "请假原因")
    private String leaveReason;

    @ApiModelProperty(value = "请假开始时间")
    private String startTime;

    @ApiModelProperty(value = "请假结束时间")
    private String endTime;

    @ApiModelProperty(value = "审批状态（0-待审批，1-已通过，2-已拒绝）")
    private String status;

    @ApiModelProperty(value = "审批意见")
    private String approvalOpinion;

    @ApiModelProperty(value = "创建时间")
    private String createTime;
}
