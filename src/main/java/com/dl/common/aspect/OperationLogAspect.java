package com.dl.common.aspect;

import com.dl.common.annotation.OperationLog;
import com.dl.context.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 操作日志切面
 */
@Aspect
@Component
@Slf4j
public class OperationLogAspect {

    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.dl.common.annotation.OperationLog)")
    public void operationLogPointCut() {
    }

    /**
     * 在方法返回后记录日志
     */
    @AfterReturning(value = "operationLogPointCut()")
    public void saveOperationLog(JoinPoint joinPoint) {
        try {
            // 获取当前登录用户ID
            Long userId = BaseContext.getCurrentId();

            // 获取方法签名
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();

            // 获取操作日志注解
            OperationLog operationLog = method.getAnnotation(OperationLog.class);
            if (operationLog != null) {
                String value = operationLog.value();
                OperationLog.OperationType operationType = operationLog.operationType();

                // 记录日志
                log.info("用户ID：{}，操作：{}，类型：{}", userId, value, operationType);
                
                // TODO: 这里可以将日志保存到数据库
            }
        } catch (Exception e) {
            log.error("记录操作日志失败：{}", e.getMessage());
        }
    }
} 