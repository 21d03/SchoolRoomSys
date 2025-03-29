package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("新增宿管DTO")
public class HouseMasterAddDTO {

    @ApiModelProperty(value = "宿管ID", required = true)
    private String hmId;

    @ApiModelProperty(value = "宿管姓名", required = true)
    private String hmName;

    @ApiModelProperty(value = "性别", required = true)
    private String hmSex;

    @ApiModelProperty(value = "联系电话", required = true)
    private String hmPhone;

    @ApiModelProperty(value = "管理的宿舍楼ID", required = false)
    private String buildId;
}
