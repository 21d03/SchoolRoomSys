package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("修改教师信息DTO")
public class TeacherUpdateDTO {

    @ApiModelProperty(value = "教师ID", required = true)
    private String teacherId;

    @ApiModelProperty(value = "联系电话", required = true)
    private String phone;

    @ApiModelProperty(value = "是否启用 1-启用 0-禁用", required = true)
    private String isUsed;
}
