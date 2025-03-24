package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "宿舍楼查询参数")
public class RoomBuildQueryDTO {
    
    @ApiModelProperty("当前页码，默认1")
    private Integer pageNum = 1;
    
    @ApiModelProperty("每页条数，默认10")
    private Integer pageSize = 10;
    
    @ApiModelProperty("楼栋名称，支持模糊查询")
    private String buildName;
    
    @ApiModelProperty("状态：1使用 0暂停使用")
    private String isUsed;
} 