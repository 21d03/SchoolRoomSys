package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("新增维修人员DTO")
public class RepairPeopleAddDTO {

    @ApiModelProperty(value = "维修人员ID", required = true)
    private String rpId;

    @ApiModelProperty(value = "维修人员姓名", required = true)
    private String rpName;

    @ApiModelProperty(value = "性别", required = true)
    private String rpSex;

    @ApiModelProperty(value = "联系电话", required = true)
    private String rpPhone;

    @ApiModelProperty(value = "所属校区", required = true)
    private String campus;
    
    @ApiModelProperty(value = "是否启用 1-启用 0-禁用", required = true)
    private String isUsed;
}
