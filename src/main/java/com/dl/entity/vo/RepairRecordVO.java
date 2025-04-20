package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("报修记录VO")
public class RepairRecordVO {
    
    @ApiModelProperty(value = "报修申请ID")
    private String id;
    
    @ApiModelProperty(value = "学生ID")
    private String studentId;
    
    @ApiModelProperty(value = "报修类型", notes = "1-宿舍物品 2-公共物品")
    private String repairType;
    
    @ApiModelProperty(value = "宿舍号", notes = "报修类型为1时有值")
    private String roomId;
    
    @ApiModelProperty(value = "公共区域", notes = "报修类型为2时有值")
    private String publicArea;
    
    @ApiModelProperty(value = "报修物品名称")
    private String itemName;
    
    @ApiModelProperty(value = "问题描述")
    private String description;
    
    @ApiModelProperty(value = "图片地址", notes = "多个用逗号分隔")
    private String images;
    
    @ApiModelProperty(value = "宿管ID")
    private String hmId;
    
    @ApiModelProperty(value = "宿管姓名")
    private String hmName;
    
    @ApiModelProperty(value = "宿管审批状态", notes = "0-待审批 1-已通过 2-已驳回")
    private String hmStatus;
    
    @ApiModelProperty(value = "宿管审批意见")
    private String hmOpinion;
    
    @ApiModelProperty(value = "宿管审批时间", notes = "格式：yyyy-MM-dd HH:mm:ss")
    private String hmTime;
    
    @ApiModelProperty(value = "维修人员ID")
    private String rpId;
    
    @ApiModelProperty(value = "维修人员姓名")
    private String rpName;
    
    @ApiModelProperty(value = "维修状态", notes = "0-待维修 1-已完成 2-已驳回")
    private String rpStatus;
    
    @ApiModelProperty(value = "维修意见")
    private String rpOpinion;
    
    @ApiModelProperty(value = "维修完成时间", notes = "格式：yyyy-MM-dd HH:mm:ss")
    private String rpTime;
    
    @ApiModelProperty(value = "创建时间", notes = "格式：yyyy-MM-dd HH:mm:ss")
    private String createTime;
    
    @ApiModelProperty(value = "是否可以催促宿管", notes = "true-可以催促 false-不可以催促")
    private Boolean canUrgeHm;
    
    @ApiModelProperty(value = "是否可以催促维修人员", notes = "true-可以催促 false-不可以催促")
    private Boolean canUrgeRp;
} 