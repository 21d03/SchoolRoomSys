package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "专业信息")
public class ProfessionVO {

    @ApiModelProperty(value = "专业名称")
    private String profession;
} 