package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "班级信息查询参数")
public class ClassInfoQueryDTO {

    @ApiModelProperty(value = "页码", example = "1")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页数量", example = "10")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "学院名称")
    private String collegeName;

    @ApiModelProperty(value = "专业名称")
    private String profession;

    @ApiModelProperty(value = "班级名称")
    private String className;
} 