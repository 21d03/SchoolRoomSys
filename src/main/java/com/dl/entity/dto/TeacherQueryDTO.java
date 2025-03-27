package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "辅导员查询参数")
public class TeacherQueryDTO {
    
    @ApiModelProperty(value = "辅导员姓名，支持模糊查询")
    private String teacherName;
    
    @ApiModelProperty(value = "所属学院")
    private String college;
} 