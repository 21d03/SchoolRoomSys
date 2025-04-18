package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("报修审批单详情VO")
public class RepairApprovalDetailVO {
    
    @ApiModelProperty(value = "主键ID", notes = "报修审批单唯一标识")
    private String id;
    
    @ApiModelProperty(value = "申请学生ID", notes = "提交报修申请的学生ID")
    private String studentId;
    
    @ApiModelProperty(value = "学生姓名", notes = "提交报修申请的学生姓名")
    private String studentName;
    
    @ApiModelProperty(value = "报修类型", notes = "1-宿舍物品 2-公共物品")
    private String repairType;
    
    @ApiModelProperty(value = "宿舍ID", notes = "宿舍物品报修时的宿舍ID")
    private String roomId;
    
    @ApiModelProperty(value = "公共区域", notes = "公共物品报修时的区域描述")
    private String publicArea;
    
    @ApiModelProperty(value = "物品名称", notes = "报修的物品名称")
    private String itemName;
    
    @ApiModelProperty(value = "问题描述", notes = "报修物品的问题描述")
    private String description;
    
    @ApiModelProperty(value = "图片地址", notes = "报修上传的图片地址，多个用逗号分隔")
    private String images;
    
    @ApiModelProperty(value = "宿管ID", notes = "处理该报修的宿管ID")
    private String hmId;
    
    @ApiModelProperty(value = "宿管姓名", notes = "处理该报修的宿管姓名")
    private String hmName;
    
    @ApiModelProperty(value = "宿管审批状态", notes = "0-待审批 1-已通过 2-已驳回")
    private String hmStatus;
    
    @ApiModelProperty(value = "宿管审批意见", notes = "宿管对报修的处理意见")
    private String hmOpinion;
    
    @ApiModelProperty(value = "宿管审批时间", notes = "宿管处理报修的时间，格式：yyyy-MM-dd HH:mm:ss")
    private String hmTime;
    
    @ApiModelProperty(value = "维修人员ID", notes = "负责维修的人员ID")
    private String rpId;
    
    @ApiModelProperty(value = "维修人员姓名", notes = "负责维修的人员姓名")
    private String rpName;
    
    @ApiModelProperty(value = "维修状态", notes = "0-待维修 1-已完成 2-已驳回")
    private String rpStatus;
    
    @ApiModelProperty(value = "维修意见", notes = "维修人员的处理意见")
    private String rpOpinion;
    
    @ApiModelProperty(value = "维修完成时间", notes = "维修完成的时间，格式：yyyy-MM-dd HH:mm:ss")
    private String rpTime;
    
    @ApiModelProperty(value = "创建时间", notes = "报修申请创建的时间，格式：yyyy-MM-dd HH:mm:ss")
    private String createTime;
    
    @ApiModelProperty(value = "更新时间", notes = "报修申请最后更新的时间，格式：yyyy-MM-dd HH:mm:ss")
    private String updateTime;
} 