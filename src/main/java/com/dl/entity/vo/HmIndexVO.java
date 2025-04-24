package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 宿管首页数据统计VO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "HmIndexVO", description = "宿管首页数据统计VO")
public class HmIndexVO {
    
    @ApiModelProperty(value = "待处理报修数")
    private Integer pendingRepairCount;
    
    @ApiModelProperty(value = "房间总数")
    private Integer totalRoomCount;
}