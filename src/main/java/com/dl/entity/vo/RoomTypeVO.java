package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "宿舍类型查询结果")
public class RoomTypeVO {

    @ApiModelProperty(value = "宿舍楼ID")
    private String buildId;
    
    @ApiModelProperty(value = "房间号")
    private String roomId;
    
    @ApiModelProperty(value = "几人寝")
    private String roomType;
} 