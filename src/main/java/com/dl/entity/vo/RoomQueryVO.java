package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "宿舍查询结果")
public class RoomQueryVO {
    
    @ApiModelProperty("宿舍号")
    private String roomId;
    
    @ApiModelProperty("几人寝")
    private String roomType;
    
    @ApiModelProperty("已入住人数")
    private Integer occupiedCount;
    
    @ApiModelProperty("空余床位")
    private Integer availableBeds;
} 