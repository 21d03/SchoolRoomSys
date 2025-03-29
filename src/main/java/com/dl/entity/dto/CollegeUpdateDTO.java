package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("学院编辑DTO")
public class CollegeUpdateDTO {

    @ApiModelProperty(value = "学院ID", required = true)
    private String collegeId;

    @ApiModelProperty(value = "原学院名称", required = true)
    private String oldCollegeName;

    @ApiModelProperty(value = "新学院名称", required = true)
    private String newCollegeName;
}
