package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "宿舍学生信息")
public class DormStudentVO {

    @ApiModelProperty(value = "宿舍类型（几人间）")
    private String roomType;
    
    @ApiModelProperty(value = "学生ID")
    private String studentId;
    
    @ApiModelProperty(value = "学生姓名")
    private String studentName;
    
    @ApiModelProperty(value = "性别")
    private String gender;
    
    @ApiModelProperty(value = "床位号")
    private String bedNo;
    
    @ApiModelProperty(value = "联系电话")
    private String phone;
    
    @ApiModelProperty(value = "专业")
    private String profession;
    
    @ApiModelProperty(value = "班级")
    private String className;
} 