package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("宿舍资源总览VO")
public class DormResourceOverviewVO {

    @ApiModelProperty(value = "宿舍楼总数")
    private Integer buildingCount;

    @ApiModelProperty(value = "房间总数")
    private Integer roomCount;

    @ApiModelProperty(value = "床位总数")
    private Integer bedCount;

    @ApiModelProperty(value = "使用率，小数表示，如0.85表示85%")
    private Double usageRate;
}
