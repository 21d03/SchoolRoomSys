package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("报修申请DTO")
public class RepairApplicationDTO {
    
    @ApiModelProperty(value = "学生ID", required = true, notes = "申请报修的学生ID")
    private String studentId;
    
    @ApiModelProperty(value = "报修类型", required = true, notes = "1-宿舍物品 2-公共物品")
    private String repairType;
    
    @ApiModelProperty(value = "宿舍号", notes = "报修类型为1时必填")
    private String roomId;
    
    @ApiModelProperty(value = "公共区域", notes = "报修类型为2时必填")
    private String publicArea;
    
    @ApiModelProperty(value = "报修物品名称", required = true)
    private String itemName;
    
    @ApiModelProperty(value = "问题描述", required = true)
    private String description;
    
    @ApiModelProperty(value = "图片地址", notes = "多个图片地址用逗号分隔")
    private String images;
} 