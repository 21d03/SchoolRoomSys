package com.dl.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "通用返回结果")
public class Result<T> {
    
    @ApiModelProperty("状态码：1成功，0失败")
    private Integer code;
    
    @ApiModelProperty("错误信息")
    private String msg;
    
    @ApiModelProperty("数据")
    private T data;
    
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.code = 1;
        result.data = data;
        return result;
    }
    
    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.code = 0;
        result.msg = msg;
        return result;
    }
} 