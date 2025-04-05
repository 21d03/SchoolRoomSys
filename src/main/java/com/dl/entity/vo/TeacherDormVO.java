package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "教师宿舍信息")
public class TeacherDormVO {

    @ApiModelProperty(value = "楼栋ID")
    private String buildId;
    
    @ApiModelProperty(value = "房间号")
    private String roomId;
    
    @ApiModelProperty(value = "宿舍全称")
    private String dormitory;
} 