package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("学院查询DTO")
public class CollegeQueryDTO {

    @ApiModelProperty(value = "页码", example = "1")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页数量", example = "10")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "学院ID")
    private String collegeId;

    @ApiModelProperty(value = "学院名称")
    private String collegeName;
}
