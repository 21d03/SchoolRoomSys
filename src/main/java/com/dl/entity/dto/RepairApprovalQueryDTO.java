package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("报修审批查询DTO")
public class RepairApprovalQueryDTO {

    @ApiModelProperty(value = "页码", example = "1")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页数量", example = "10")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "学生ID")
    private String studentId;

    @ApiModelProperty(value = "学生姓名")
    private String studentName;

    @ApiModelProperty(value = "宿管审批状态（0-待审批，1-已通过，2-已拒绝）")
    private String hmStatus;

    @ApiModelProperty(value = "维修人员审批状态（0-待审批，1-已完成，2-已拒绝）")
    private String rpStatus;
}
