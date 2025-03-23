package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "学校管理员首页数据")
public class SchoolIndexVO {
    
    @ApiModelProperty("宿舍楼总数")
    private Integer buildingTotal;
    
    @ApiModelProperty("正常使用宿舍楼数")
    private Integer buildingNormal;
    
    @ApiModelProperty("暂停使用宿舍楼数")
    private Integer buildingStopped;
    
    @ApiModelProperty("房间总数")
    private Integer roomTotal;
    
    @ApiModelProperty("正常使用房间数")
    private Integer roomNormal;
    
    @ApiModelProperty("暂停使用房间数")
    private Integer roomStopped;
    
    @ApiModelProperty("审批总数")
    private Integer approvalTotal;
    
    @ApiModelProperty("已处理审批数")
    private Integer approvalProcessed;
    
    @ApiModelProperty("未处理审批数")
    private Integer approvalPending;
} 