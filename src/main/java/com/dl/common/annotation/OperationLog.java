package com.dl.common.annotation;

import java.lang.annotation.*;

/**
 * 操作日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {
    /**
     * 操作描述
     */
    String value() default "";

    /**
     * 操作类型
     */
    public enum OperationType {
        /**
         * 查询
         */
        QUERY,
        /**
         * 新增
         */
        INSERT,
        /**
         * 修改
         */
        UPDATE,
        /**
         * 删除
         */
        DELETE,
        /**
         * 登录
         */
        LOGIN,
        /**
         * 登出
         */
        LOGOUT
    }

    /**
     * 操作类型
     */
    OperationType operationType() default OperationType.QUERY;
} 