package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("宿舍楼房间分布VO")
public class BuildingRoomDistributionVO {

    @ApiModelProperty(value = "宿舍楼名称")
    private String buildingName;

    @ApiModelProperty(value = "总房间数")
    private Integer totalRooms;

    @ApiModelProperty(value = "已使用房间数")
    private Integer usedRooms;

    @ApiModelProperty(value = "空闲房间数")
    private Integer availableRooms;

    @ApiModelProperty(value = "维修中房间数")
    private Integer underMaintenanceRooms;
}
