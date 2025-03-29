package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("维修人员查询DTO")
public class RepairPeopleQueryDTO {

    @ApiModelProperty(value = "页码", example = "1")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页数量", example = "10")
    private Integer pageSize = 10;

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
}
