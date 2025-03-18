package com.dl.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dongliang
 * @date 2024/09/22 20:40:40
 * @description
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "老师登录返回参数")
public class SchoolLoginVO {
    @ApiModelProperty("主键值")
    private Long id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("jwt令牌")
    private String token;
}
