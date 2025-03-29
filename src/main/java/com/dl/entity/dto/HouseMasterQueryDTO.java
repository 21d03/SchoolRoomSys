package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("宿管查询DTO")
public class HouseMasterQueryDTO {

    @ApiModelProperty(value = "页码", example = "1")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页数量", example = "10")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "宿管ID")
    private String masterId;

    @ApiModelProperty(value = "宿管姓名")
    private String masterName;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "性别")
    private String sex;
}
