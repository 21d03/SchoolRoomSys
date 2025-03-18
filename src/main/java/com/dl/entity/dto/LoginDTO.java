package com.dl.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(description = "登录参数")
public class LoginDTO {
    @NotBlank(message = "用户类型不能为空")
    @ApiModelProperty(value = "用户类型：1-学校老师 2-学院老师 3-学生 4-宿管 5-维修人员", required = true)
    private String userType;
    
    @NotBlank(message = "用户ID不能为空")
    @ApiModelProperty(value = "用户ID/工号/学号", required = true)
    private String userId;
    
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;
} 