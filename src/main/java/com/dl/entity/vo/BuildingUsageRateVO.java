package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("宿舍楼使用率VO")
public class BuildingUsageRateVO {

    @ApiModelProperty(value = "宿舍楼名称")
    private String buildingName;

    @ApiModelProperty(value = "使用率，小数表示，如0.85表示85%")
    private Double usageRate;
}
