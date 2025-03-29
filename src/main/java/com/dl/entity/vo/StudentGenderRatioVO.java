package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("学生性别比例VO")
public class StudentGenderRatioVO {

    @ApiModelProperty(value = "男生人数")
    private Integer maleCount;

    @ApiModelProperty(value = "女生人数")
    private Integer femaleCount;
}
