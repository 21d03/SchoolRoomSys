package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "学生列表项")
public class StudentListVO {

    @ApiModelProperty(value = "学号")
    private String studentId;
    
    @ApiModelProperty(value = "学生姓名")
    private String name;
    
    @ApiModelProperty(value = "性别")
    private String gender;
    
    @ApiModelProperty(value = "专业")
    private String profession;
    
    @ApiModelProperty(value = "班级名称")
    private String className;
    
    @ApiModelProperty(value = "宿舍")
    private String dormitory;
    
    @ApiModelProperty(value = "联系电话")
    private String phone;
} 