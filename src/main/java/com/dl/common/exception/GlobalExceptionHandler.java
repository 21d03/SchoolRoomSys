package com.dl.common.exception;

import com.dl.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex 业务异常
     * @return 统一返回格式
     */
    @ExceptionHandler(BusinessException.class)
    public Result<String> handleBusinessException(BusinessException ex) {
        log.error("业务异常：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 捕获系统异常
     * @param ex 系统异常
     * @return 统一返回格式
     */
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception ex) {
        log.error("系统异常：{}", ex.getMessage());
        return Result.error("系统错误，请联系管理员");
    }
} 