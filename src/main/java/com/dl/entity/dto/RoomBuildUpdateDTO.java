package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 宿舍楼修改DTO
 */
@Data
@ApiModel(value = "宿舍楼修改参数")
public class RoomBuildUpdateDTO {

    @ApiModelProperty(value = "宿舍楼ID", required = true)
    @NotBlank(message = "宿舍楼ID不能为空")
    private String buildId;

    @ApiModelProperty(value = "宿舍楼名称", required = true)
    @NotBlank(message = "宿舍楼名称不能为空")
    private String buildName;

    @ApiModelProperty(value = "修改前的宿管ID")
    private String oldHmId;

    @ApiModelProperty(value = "修改后的宿管ID")
    private String newHmId;
} 