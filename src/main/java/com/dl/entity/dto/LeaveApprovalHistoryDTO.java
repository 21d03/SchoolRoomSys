package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "请假审批历史查询参数")
public class LeaveApprovalHistoryDTO {

    @ApiModelProperty(value = "教师ID", required = true)
    private String teacherId;
    
    @ApiModelProperty(value = "学生学号（支持模糊查询）")
    private String studentId;
    
    @ApiModelProperty(value = "学生姓名（支持模糊查询）")
    private String studentName;
    
    @ApiModelProperty(value = "审批状态（1-已通过，2-已拒绝）")
    private String status;
    
    @ApiModelProperty(value = "审批开始日期（格式：yyyy-MM-dd）")
    private String submitStartDate;
    
    @ApiModelProperty(value = "审批结束日期（格式：yyyy-MM-dd）")
    private String submitEndDate;
    
    @ApiModelProperty(value = "页码", required = true)
    private Integer pageNum = 1;
    
    @ApiModelProperty(value = "每页大小", required = true)
    private Integer pageSize = 10;
} 