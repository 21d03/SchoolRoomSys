package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("报修审批VO")
public class RepairApprovalVO {

    @ApiModelProperty(value = "审批ID")
    private String approvalId;

    @ApiModelProperty(value = "学生ID")
    private String studentId;

    @ApiModelProperty(value = "学生姓名")
    private String studentName;

    @ApiModelProperty(value = "报修内容")
    private String repairContent;

    @ApiModelProperty(value = "宿舍楼")
    private String buildName;

    @ApiModelProperty(value = "房间号")
    private String roomId;

    @ApiModelProperty(value = "宿管审批状态（0-待审批，1-已通过，2-已拒绝）")
    private String hmStatus;

    @ApiModelProperty(value = "宿管审批意见")
    private String hmOpinion;

    @ApiModelProperty(value = "维修人员审批状态（0-待审批，1-已完成，2-已拒绝）")
    private String rpStatus;

    @ApiModelProperty(value = "维修人员审批意见")
    private String rpOpinion;

    @ApiModelProperty(value = "创建时间")
    private String createTime;
}
