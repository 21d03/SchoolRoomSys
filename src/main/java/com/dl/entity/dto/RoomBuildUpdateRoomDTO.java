package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * 修改房间DTO
 * </p>
 *
 * @author dongliang
 * @since 2024-11-01
 */
@Data
@ApiModel(value = "RoomBuildUpdateRoomDTO", description = "修改宿舍房间DTO")
public class RoomBuildUpdateRoomDTO {

    @ApiModelProperty(value = "宿舍楼ID", required = true)
    @NotBlank(message = "宿舍楼ID不能为空")
    private String buildId;

    @ApiModelProperty(value = "房间号", required = true)
    @NotBlank(message = "房间号不能为空")
    private String roomId;

    @ApiModelProperty(value = "是否混寝 1混寝 2不混", required = true)
    @NotBlank(message = "是否混寝不能为空")
    @Pattern(regexp = "[1|2]", message = "是否混寝参数错误")
    private String isMixed;

    @ApiModelProperty(value = "几人寝", required = true)
    @NotBlank(message = "几人寝不能为空")
    private String roomType;
} 