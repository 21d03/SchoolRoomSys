package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("学院学生分布VO")
public class CollegeStudentDistributionVO {

    @ApiModelProperty(value = "学院ID")
    private String collegeId;

    @ApiModelProperty(value = "学院名称")
    private String collegeName;

    @ApiModelProperty(value = "该学院学生人数")
    private Integer studentCount;
}
