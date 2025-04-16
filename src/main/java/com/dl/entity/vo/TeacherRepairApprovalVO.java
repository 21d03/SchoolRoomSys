package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("教师报修管理查询VO")
public class TeacherRepairApprovalVO {

    @ApiModelProperty(value = "报修申请单ID")
    private String id;
    
    @ApiModelProperty(value = "学生ID")
    private String studentId;
    
    @ApiModelProperty(value = "学生姓名")
    private String stuName;
    
    @ApiModelProperty(value = "班级")
    private String className;
    
    @ApiModelProperty(value = "报修类型")
    private String repairType;
    
    @ApiModelProperty(value = "报修物品")
    private String repairItem;
    
    @ApiModelProperty(value = "创建时间")
    private String createTime;
} 