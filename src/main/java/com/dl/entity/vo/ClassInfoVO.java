package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "班级信息")
public class ClassInfoVO {

    @ApiModelProperty(value = "学院ID")
    private String collegeId;

    @ApiModelProperty(value = "学院名称")
    private String collegeName;

    @ApiModelProperty(value = "专业名称")
    private String profession;

    @ApiModelProperty(value = "班级名称")
    private String className;

    @ApiModelProperty(value = "分管教师ID")
    private String teacherId;

    @ApiModelProperty(value = "分管教师姓名")
    private String teacherName;
} 