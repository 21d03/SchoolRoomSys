package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "辅导员信息")
public class TeacherVO {

    @ApiModelProperty(value = "辅导员ID")
    private String teacherId;

    @ApiModelProperty(value = "辅导员姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "联系方式")
    private String phone;

    @ApiModelProperty(value = "所属学院")
    private String college;
} 