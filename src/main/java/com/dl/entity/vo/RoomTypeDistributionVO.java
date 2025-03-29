package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("房间类型分布VO")
public class RoomTypeDistributionVO {

    @ApiModelProperty(value = "房间类型名称")
    private String typeName;

    @ApiModelProperty(value = "数量")
    private Integer count;
}
