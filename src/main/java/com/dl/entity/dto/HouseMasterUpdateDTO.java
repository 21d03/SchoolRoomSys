package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("修改宿管DTO")
public class HouseMasterUpdateDTO {

    @ApiModelProperty(value = "宿管ID", required = true)
    private String hmId;

    @ApiModelProperty(value = "联系电话", required = true)
    private String hmPhone;

    @ApiModelProperty(value = "是否启用 1-启用 0-禁用", required = true)
    private String isUsed;
}
