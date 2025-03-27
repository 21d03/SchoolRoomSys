package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "学生信息")
public class StudentVO {

    @ApiModelProperty(value = "学生ID")
    private String stuId;

    @ApiModelProperty(value = "学生姓名")
    private String stuName;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "所属学院")
    private String college;

    @ApiModelProperty(value = "专业")
    private String profession;

    @ApiModelProperty(value = "班级")
    private String classRoom;

    @ApiModelProperty(value = "辅导员ID")
    private String teacherId;

    @ApiModelProperty(value = "辅导员姓名")
    private String teacherName;

    @ApiModelProperty(value = "所属宿舍楼ID")
    private String buildId;

    @ApiModelProperty(value = "所属宿舍号")
    private String roomId;
} 