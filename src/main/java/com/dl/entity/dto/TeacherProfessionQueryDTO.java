package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "教师查询专业参数")
public class TeacherProfessionQueryDTO {

    @ApiModelProperty(value = "教师ID", required = true)
    private String teacherId;
    
    @ApiModelProperty(value = "专业名称")
    private String profession;
} 