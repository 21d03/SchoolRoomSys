package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("教师报修管理查询DTO")
public class TeacherRepairApprovalQueryDTO {

    @ApiModelProperty(value = "页码", example = "1")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页数量", example = "10")
    private Integer pageSize = 10;
    
    @ApiModelProperty(value = "教师ID", required = true)
    private String teacherId;
    
    @ApiModelProperty(value = "学生姓名")
    private String stuName;
    
    @ApiModelProperty(value = "报修类型 1-宿舍物品 2-公共物品")
    private String repairType;
    
    @ApiModelProperty(value = "宿舍ID")
    private String roomId;
    
    @ApiModelProperty(value = "物品名称")
    private String itemName;
} 