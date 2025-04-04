package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "教师查询学生列表参数")
public class TeacherStudentQueryDTO {

    @ApiModelProperty(value = "教师ID", required = true)
    private String teacherId;

    @ApiModelProperty(value = "页码", required = true)
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页大小", required = true)
    private Integer pageSize = 10;

    @ApiModelProperty(value = "学号（支持模糊查询）")
    private String studentId;

    @ApiModelProperty(value = "学生姓名（支持模糊查询）")
    private String name;

    @ApiModelProperty(value = "专业（支持模糊查询）")
    private String profession;

    @ApiModelProperty(value = "班级名称（支持模糊查询）")
    private String className;
} 