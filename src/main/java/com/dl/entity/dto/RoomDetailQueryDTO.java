package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(description = "宿舍详情查询参数")
public class RoomDetailQueryDTO {
    
    @NotBlank(message = "宿舍楼ID不能为空")
    @ApiModelProperty(value = "宿舍楼ID", required = true)
    private String buildId;
    
    @NotBlank(message = "宿舍号不能为空")
    @ApiModelProperty(value = "宿舍号", required = true)
    private String roomId;
} 