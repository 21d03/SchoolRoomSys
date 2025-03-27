package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "房间详情信息")
public class RoomDetailVO {

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

    @ApiModelProperty(value = "入住学生列表")
    private List<StudentInfoVO> students;
}

@Data
class StudentInfoVO {
    @ApiModelProperty(value = "学生ID")
    private String studentId;

    @ApiModelProperty(value = "学生姓名")
    private String studentName;

    @ApiModelProperty(value = "学生性别")
    private String studentSex;

    @ApiModelProperty(value = "联系电话")
    private String studentPhone;

    @ApiModelProperty(value = "所属学院")
    private String collegeName;

    @ApiModelProperty(value = "辅导员ID")
    private String teacherId;

    @ApiModelProperty(value = "辅导员姓名")
    private String teacherName;
    
    @ApiModelProperty(value = "辅导员电话")
    private String teacherPhone;
} 