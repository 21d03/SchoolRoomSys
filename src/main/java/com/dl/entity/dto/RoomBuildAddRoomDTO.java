package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * 新增房间DTO
 * </p>
 *
 * @author dongliang
 * @since 2024-11-01
 */
@Data
@ApiModel(value = "RoomBuildAddRoomDTO", description = "新增宿舍房间DTO")
public class RoomBuildAddRoomDTO {

    @ApiModelProperty(value = "宿舍楼ID", required = true)
    @NotBlank(message = "宿舍楼ID不能为空")
    private String buildId;

    @ApiModelProperty(value = "楼层", required = true)
    @NotBlank(message = "楼层不能为空")
    private String layerNumber;

    @ApiModelProperty(value = "房间号", required = true)
    @NotBlank(message = "房间号不能为空")
    private String roomId;

    @ApiModelProperty(value = "是否混寝 1混寝 2不混", required = true)
    @NotBlank(message = "是否混寝不能为空")
    @Pattern(regexp = "[1|2]", message = "是否混寝参数错误")
    private String isMixed;

    @ApiModelProperty(value = "入住学生的学院id，如果是混寝，用英文逗号分隔", required = false)
    private String collegeIds;

    @ApiModelProperty(value = "所属老师id", required = false)
    private String manageTeacherId;

    @ApiModelProperty(value = "几人寝", required = true)
    @NotBlank(message = "几人寝不能为空")
    private String roomType;

    @ApiModelProperty(value = "使用状态 1-正常使用 0-暂停使用", required = true)
    @NotBlank(message = "使用状态不能为空")
    @Pattern(regexp = "[0|1]", message = "使用状态参数错误")
    private String status;
} 