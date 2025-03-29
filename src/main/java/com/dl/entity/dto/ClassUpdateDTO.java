package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("班级编辑DTO")
public class ClassUpdateDTO {

    @ApiModelProperty(value = "学院名称", required = true)
    private String collegeName;

    @ApiModelProperty(value = "原专业名称", required = true)
    private String oldProfession;

    @ApiModelProperty(value = "新专业名称", required = true)
    private String newProfession;

    @ApiModelProperty(value = "原班级名称", required = true)
    private String oldClassName;

    @ApiModelProperty(value = "新班级名称", required = true)
    private String newClassName;
}
