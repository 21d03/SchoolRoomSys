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
@ApiModel(description = "宿管登录返回数据")
public class HouseMasterLoginVO {
    
    @ApiModelProperty("宿管ID")
    private String userId;
    
    @ApiModelProperty("宿管姓名")
    private String userName;
    
    @ApiModelProperty("宿管姓名")
    private String name;
    
    @ApiModelProperty("性别")
    private String sex;
    
    @ApiModelProperty("联系方式")
    private String phone;
    
    @ApiModelProperty("管理的宿舍楼ID")
    private String buildId;
    
    @ApiModelProperty("用户类型：4-宿管")
    private String userType;
    
    @ApiModelProperty("token令牌")
    private String token;
} 