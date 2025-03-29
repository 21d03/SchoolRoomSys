package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("宿管VO")
public class HouseMasterVO {

    @ApiModelProperty(value = "宿管ID")
    private String masterId;

    @ApiModelProperty(value = "宿管姓名")
    private String masterName;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "管理的宿舍楼")
    private String buildingNames;

    @ApiModelProperty(value = "是否启用")
    private String isUsed;
}
