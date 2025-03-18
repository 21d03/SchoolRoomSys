package com.dl.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author dongliang
 * @date 2024/09/22 19:08:08
 * @description 后端统一返回结果
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {

    //编码：1成功 0和其他数字为失败
    private Integer code;

    //错误信息
    private String msg;

    //数据
    private T data;

    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.code = 0;
        return result;
    }

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.data = object;
        result.code = 0;
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 1;
        return result;
    }
}
