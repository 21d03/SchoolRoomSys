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
@ApiModel(description = "宿舍楼信息")
public class RoomBuildVO {
    
    @ApiModelProperty("楼栋编号")
    private String buildId;
    
    @ApiModelProperty("楼栋名称")
    private String buildName;
    
    @ApiModelProperty("楼层数")
    private String layerNumber;
    
    @ApiModelProperty("总房间数")
    private String totalRoomNum;
    
    @ApiModelProperty("负责宿管名称")
    private String hmName;
    
    @ApiModelProperty("入住率")
    private String occupancyRate;
    
    @ApiModelProperty("状态：1使用 0暂停使用")
    private String isUsed;
} 