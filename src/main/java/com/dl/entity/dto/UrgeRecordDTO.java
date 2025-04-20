package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("催促记录DTO")
public class UrgeRecordDTO {
    
    @ApiModelProperty(value = "催促人ID", required = true, notes = "发起催促的用户ID")
    private String fromId;
    
    @ApiModelProperty(value = "接收人ID", required = true, notes = "被催促的用户ID")
    private String toId;
    
    @ApiModelProperty(value = "审批单ID", required = true, notes = "报修单或请假单ID")
    private String approvalId;
    
    @ApiModelProperty(value = "审批类型", required = true, notes = "1-报修 2-请假", example = "1")
    private String approvalType = "1";
    
    @ApiModelProperty(value = "催促类型", required = true, notes = "1-学生催促宿管 2-学生催促维修 3-宿管催促维修", example = "1")
    private String urgeType;
    
    @ApiModelProperty(value = "催促内容", required = true, notes = "催促的具体内容")
    private String urgeContent;
} 