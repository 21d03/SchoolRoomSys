package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("分配班级DTO")
public class AssignClassDTO {

    @ApiModelProperty(value = "学院名称", required = true)
    private String collegeName;

    @ApiModelProperty(value = "专业名称", required = true)
    private String profession;

    @ApiModelProperty(value = "班级名称", required = true)
    private String className;

    @ApiModelProperty(value = "教师ID", required = true)
    private String teacherId;

    @ApiModelProperty(value = "教师姓名", required = true)
    private String teacherName;
}
