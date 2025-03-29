package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("维修人员VO")
public class RepairPeopleVO {

    @ApiModelProperty(value = "维修人员ID")
    private String rpId;

    @ApiModelProperty(value = "维修人员姓名")
    private String rpName;

    @ApiModelProperty(value = "性别")
    private String rpSex;

    @ApiModelProperty(value = "联系电话")
    private String rpPhone;

    @ApiModelProperty(value = "所属校区")
    private String campus;

    @ApiModelProperty(value = "是否启用")
    private String isUsed;
}
