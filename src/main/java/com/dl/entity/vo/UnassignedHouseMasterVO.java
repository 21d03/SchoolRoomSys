package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("未分配宿舍楼的宿管信息")
public class UnassignedHouseMasterVO {

    @ApiModelProperty("宿管ID")
    private String hmId;

    @ApiModelProperty("宿管姓名")
    private String hmName;
    
    @ApiModelProperty("性别")
    private String hmSex;
    
    @ApiModelProperty("联系方式")
    private String hmPhone;
} 