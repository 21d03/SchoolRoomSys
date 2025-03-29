package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("修改维修人员DTO")
public class RepairPeopleUpdateDTO {

    @ApiModelProperty(value = "维修人员ID", required = true)
    private String rpId;

    @ApiModelProperty(value = "联系电话", required = true)
    private String rpPhone;

    @ApiModelProperty(value = "是否启用 1-启用 0-禁用", required = true)
    private String isUsed;
}