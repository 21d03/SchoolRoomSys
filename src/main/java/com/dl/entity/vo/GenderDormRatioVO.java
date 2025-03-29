package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("男女宿舍比例VO")
public class GenderDormRatioVO {

    @ApiModelProperty(value = "男生宿舍数量")
    private Integer maleCount;

    @ApiModelProperty(value = "女生宿舍数量")
    private Integer femaleCount;
}
