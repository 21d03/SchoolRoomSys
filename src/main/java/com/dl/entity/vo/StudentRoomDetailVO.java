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
@ApiModel(description = "学生宿舍详情")
public class StudentRoomDetailVO {
    
    @ApiModelProperty("学生学号")
    private String stuId;
    
    @ApiModelProperty("学生姓名")
    private String stuName;
    
    @ApiModelProperty("床位号")
    private String bedNo;
    
    @ApiModelProperty("专业班级信息")
    private String majorClass;
    
    @ApiModelProperty("学生电话")
    private String phone;
    
    @ApiModelProperty("辅导员姓名")
    private String teacherName;
    
    @ApiModelProperty("辅导员ID")
    private String teacherId;
    
    @ApiModelProperty("辅导员电话")
    private String teacherPhone;
} 