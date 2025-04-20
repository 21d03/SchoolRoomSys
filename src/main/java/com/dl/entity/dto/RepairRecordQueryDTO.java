package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("报修记录查询DTO")
public class RepairRecordQueryDTO {
    
    @ApiModelProperty(value = "学生ID", required = true, notes = "查询的学生ID")
    private String studentId;
    
    @ApiModelProperty(value = "页码", example = "1")
    private Integer pageNum = 1;
    
    @ApiModelProperty(value = "每页数量", example = "10")
    private Integer pageSize = 10;
    
    @ApiModelProperty(value = "报修类型", notes = "1-宿舍物品 2-公共物品")
    private String repairType;
    
    @ApiModelProperty(value = "宿管审批状态", notes = "0-待审批 1-已通过 2-已驳回")
    private String hmStatus;
    
    @ApiModelProperty(value = "维修人员审批状态", notes = "0-待维修 1-已完成 2-已驳回")
    private String rpStatus;
    
    @ApiModelProperty(value = "报修物品名称", notes = "支持模糊查询")
    private String itemName;
} 