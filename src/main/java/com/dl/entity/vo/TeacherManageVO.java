package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "教师管理信息")
public class TeacherManageVO {

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

    @ApiModelProperty(value = "负责班级（格式：专业+班级，多个班级用逗号分隔）")
    private String classes;
} 