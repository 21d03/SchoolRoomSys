package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "房间信息")
public class RoomVO {

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

    @ApiModelProperty(value = "所属学院ID，多个用逗号分隔")
    private String collegeIds;

    @ApiModelProperty(value = "所属学院名称，多个用逗号分隔")
    private String collegeNames;

    @ApiModelProperty(value = "管理老师ID")
    private String manageTeacherId;

    @ApiModelProperty(value = "管理老师姓名")
    private String manageTeacherName;

    @ApiModelProperty(value = "几人寝")
    private String roomType;

    @ApiModelProperty(value = "使用状态 1-正常使用 0-暂停使用")
    private String status;

    @ApiModelProperty(value = "当前入住人数")
    private Integer currentStudentCount;
} 