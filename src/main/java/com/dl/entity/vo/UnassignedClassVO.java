package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("未分配辅导员的班级VO")
public class UnassignedClassVO {

    @ApiModelProperty(value = "学院ID")
    private String collegeId;

    @ApiModelProperty(value = "学院名称")
    private String collegeName;

    @ApiModelProperty(value = "专业名称")
    private String profession;

    @ApiModelProperty(value = "班级名称")
    private String className;
}
