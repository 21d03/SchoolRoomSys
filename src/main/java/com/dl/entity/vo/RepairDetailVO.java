package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "报修详情")
public class RepairDetailVO {
    
    @ApiModelProperty("报修单号")
    private String id;
    
    @ApiModelProperty("申请学生ID")
    private String studentId;
    
    @ApiModelProperty("学生姓名")
    private String studentName;
    
    @ApiModelProperty("报修类型：1-宿舍物品 2-公共物品")
    private String repairType;
    
    @ApiModelProperty("宿舍ID")
    private String roomId;
    
    @ApiModelProperty("公共区域")
    private String publicArea;
    
    @ApiModelProperty("物品名称")
    private String itemName;
    
    @ApiModelProperty("问题描述")
    private String description;
    
    @ApiModelProperty("图片路径，多个以逗号分隔")
    private String images;
    
    @ApiModelProperty("图片列表(Base64编码)")
    private List<String> imageBase64List;
    
    @ApiModelProperty("宿管ID")
    private String hmId;
    
    @ApiModelProperty("宿管姓名")
    private String hmName;
    
    @ApiModelProperty("宿管审批状态：0-待审批 1-已通过 2-已拒绝")
    private String hmStatus;
    
    @ApiModelProperty("宿管审批意见")
    private String hmOpinion;
    
    @ApiModelProperty("宿管审批时间")
    private String hmTime;
    
    @ApiModelProperty("维修人员ID")
    private String rpId;
    
    @ApiModelProperty("维修人员姓名")
    private String rpName;
    
    @ApiModelProperty("维修状态：0-待维修 1-已完成 2-已拒绝")
    private String rpStatus;
    
    @ApiModelProperty("维修意见")
    private String rpOpinion;
    
    @ApiModelProperty("维修完成时间")
    private String rpTime;
    
    @ApiModelProperty("创建时间")
    private String createTime;
    
    @ApiModelProperty("更新时间")
    private String updateTime;
} 