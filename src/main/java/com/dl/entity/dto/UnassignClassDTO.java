package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("取消分管班级DTO")
public class UnassignClassDTO {

    @ApiModelProperty(value = "学院名称", required = true)
    private String collegeName;

    @ApiModelProperty(value = "专业名称", required = true)
    private String profession;

    @ApiModelProperty(value = "班级名称", required = true)
    private String className;
}
