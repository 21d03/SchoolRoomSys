package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 宿舍楼状态变更结果
 */
@Data
@ApiModel(value = "宿舍楼状态变更结果")
public class RoomBuildStatusResultVO {
    
    @ApiModelProperty(value = "是否成功")
    private boolean success;
    
    @ApiModelProperty(value = "失败原因")
    private String reason;
    
    /**
     * 创建成功结果
     */
    public static RoomBuildStatusResultVO success() {
        RoomBuildStatusResultVO result = new RoomBuildStatusResultVO();
        result.setSuccess(true);
        return result;
    }
    
    /**
     * 创建失败结果
     * @param reason 失败原因
     */
    public static RoomBuildStatusResultVO fail(String reason) {
        RoomBuildStatusResultVO result = new RoomBuildStatusResultVO();
        result.setSuccess(false);
        result.setReason(reason);
        return result;
    }
} 