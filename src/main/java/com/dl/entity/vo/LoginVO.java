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
@ApiModel(description = "登录返回数据")
public class LoginVO {
    @ApiModelProperty("用户ID")
    private String userId;
    
    @ApiModelProperty("用户名")
    private String userName;
    
    @ApiModelProperty("姓名")
    private String name;
    
    @ApiModelProperty("用户类型：1-学校老师 2-学院老师 3-学生 4-宿管 5-维修人员")
    private String userType;
    
    @ApiModelProperty("token令牌")
    private String token;
    
    @ApiModelProperty("性别")
    private String sex;
    
    @ApiModelProperty("联系方式")
    private String phone;
    
    @ApiModelProperty("所属学院名称")
    private String collegeName;
    
    @ApiModelProperty("用户级别 0-学校管理员 1-普通教师")
    private String level;
    
    @ApiModelProperty("所管理的宿舍楼ID")
    private String buildId;
    
    @ApiModelProperty("所在校区 1梁园 2睢阳")
    private String campus;
} 