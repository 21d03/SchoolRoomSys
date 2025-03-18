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
@ApiModel(description = "学生登录返回数据")
public class StudentLoginVO {
    @ApiModelProperty(value = "学生ID")
    private String userId;
    
    @ApiModelProperty(value = "用户名")
    private String userName;
    
    @ApiModelProperty(value = "姓名")
    private String name;
    
    @ApiModelProperty(value = "联系方式")
    private String phone;
    
    @ApiModelProperty(value = "性别")
    private String sex;
    
    @ApiModelProperty(value = "所属学院名称")
    private String collegeName;
    
    @ApiModelProperty(value = "专业")
    private String profession;
    
    @ApiModelProperty(value = "班级")
    private String classRoom;
    
    @ApiModelProperty(value = "辅导员ID")
    private String teacherId;
    
    @ApiModelProperty(value = "辅导员姓名")
    private String teacherName;
    
    @ApiModelProperty(value = "辅导员联系方式")
    private String teacherPhone;
    
    @ApiModelProperty(value = "宿舍楼ID")
    private String buildId;
    
    @ApiModelProperty(value = "房间号")
    private String roomId;
    
    @ApiModelProperty(value = "token令牌")
    private String token;
    
    @ApiModelProperty(value = "用户类型")
    private String userType;
    
    @ApiModelProperty(value = "密码", hidden = true)
    private String password;
} 