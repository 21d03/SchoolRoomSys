package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("班级人数分布VO")
public class ClassStudentDistributionVO {

    @ApiModelProperty(value = "班级名称")
    private String className;

    @ApiModelProperty(value = "所属学院ID")
    private String collegeId;

    @ApiModelProperty(value = "所属学院名称")
    private String collegeName;

    @ApiModelProperty(value = "班级学生人数")
    private Integer studentCount;

    @ApiModelProperty(value = "所属专业")
    private String profession;

    @ApiModelProperty(value = "班级名称（不包含学院名称）")
    private String classNameOnly;
}
