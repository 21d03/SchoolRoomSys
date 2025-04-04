package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "班级信息")
public class ClassNameVO {

    @ApiModelProperty(value = "班级名称")
    private String className;
} 