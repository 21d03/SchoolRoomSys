package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "房间查询参数")
public class RoomQueryDTO {

    @ApiModelProperty(value = "页码", required = true)
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页大小", required = true)
    private Integer pageSize = 10;

    @ApiModelProperty(value = "宿舍楼ID")
    private String buildId;

    @ApiModelProperty(value = "宿舍楼名称")
    private String buildName;

    @ApiModelProperty(value = "楼层号")
    private String layerNumber;

    @ApiModelProperty(value = "房间号")
    private String roomId;

    @ApiModelProperty(value = "是否混寝 1混寝 2不混")
    private String isMixed;

    @ApiModelProperty(value = "几人寝")
    private String roomType;

    @ApiModelProperty(value = "使用状态 1-正常使用 0-暂停使用")
    private String status;

    @ApiModelProperty(value = "所属学院ID，支持多个，用逗号分隔")
    private String collegeIds;

    @ApiModelProperty(value = "管理老师姓名")
    private String manageTeacherName;
} 