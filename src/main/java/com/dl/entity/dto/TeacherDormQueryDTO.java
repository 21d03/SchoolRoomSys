package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "教师查询宿舍参数")
public class TeacherDormQueryDTO {

    @ApiModelProperty(value = "教师ID", required = true)
    private String teacherId;
} 