package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "宿舍学生查询参数")
public class DormStudentQueryDTO {

    @ApiModelProperty(value = "楼栋ID", required = true)
    private String buildId;
    
    @ApiModelProperty(value = "房间号", required = true)
    private String roomId;
} 