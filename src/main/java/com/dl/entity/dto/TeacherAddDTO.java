package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "教师新增参数")
public class TeacherAddDTO {

    @ApiModelProperty(value = "教师ID")
    private String teacherId;

    @ApiModelProperty(value = "教师姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "所属学院")
    private String college;

    @ApiModelProperty(value = "是否启用 1-启用 0-禁用", example = "1")
    private String isUsed;
} 