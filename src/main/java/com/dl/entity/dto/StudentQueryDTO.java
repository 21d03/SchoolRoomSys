package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "学生查询参数")
public class StudentQueryDTO {

    @ApiModelProperty(value = "页码", required = true)
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页大小", required = true)
    private Integer pageSize = 10;

    @ApiModelProperty(value = "学号")
    private String stuId;

    @ApiModelProperty(value = "学生姓名")
    private String stuName;

    @ApiModelProperty(value = "所属学院")
    private String college;

    @ApiModelProperty(value = "性别 男/女")
    private String sex;
} 