package com.dl.interceptor;

import com.dl.constant.JwtClaimsConstant;
import com.dl.context.BaseContext;
import com.dl.properties.JwtProperties;
import com.dl.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dongliang
 * @date 2024/09/22 22:18:18
 * @description
 **/
@Component
@Slf4j
public class JwtTokenSchoolInterceptor implements HandlerInterceptor {

    @Resource
    private JwtProperties jwtProperties;

    /**
     * 校验jwt
     *
     * @param request  HTTP请求对象，用于获取请求头中的jwt令牌
     * @param response HTTP响应对象，用于设置响应状态码
     * @param handler  被拦截的处理器，用于判断是否为动态方法
     * @return 如果jwt校验通过且用户ID有效，则返回true继续执行下一个拦截器或目标方法；否则返回false中断执行
     * @throws Exception 如果在处理过程中抛出异常，则直接传播该异常
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            // 当前拦截到的不是动态方法，直接放行
            return true;
        }

        // 1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getSchoolTokenName());

        // 2、校验令牌
        try {
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getSchoolSecretKey(), token);
            Long userId = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
            log.info("当前登录id:{}", userId);
            BaseContext.setCurrentId(userId);
            // 3、通过，放行
            return true;
        } catch (IllegalArgumentException | NullPointerException ex) {
            // 处理空指针或非法参数异常
            log.error("JWT解析失败: {}", ex.getMessage(), ex);
            response.setStatus(401);
            return false;
        } catch (SignatureException ex) {
            // 处理签名验证失败
            log.error("JWT签名验证失败: {}", ex.getMessage(), ex);
            response.setStatus(401);
            return false;
        } catch (ExpiredJwtException ex) {
            // 处理过期的JWT
            log.error("JWT已过期: {}", ex.getMessage(), ex);
            response.setStatus(401);
            return false;
        } catch (Exception ex) {
            // 其他未知异常
            log.error("JWT校验失败: {}", ex.getMessage(), ex);
            response.setStatus(500);
            return false;
        }
    }

}
