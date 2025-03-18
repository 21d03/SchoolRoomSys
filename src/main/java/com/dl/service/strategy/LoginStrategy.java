package com.dl.service.strategy;

import com.dl.entity.dto.LoginDTO;

public interface LoginStrategy<T> {
    /**
     * 登录处理
     * @param loginDTO 登录参数
     * @return 登录结果
     */
    T login(LoginDTO loginDTO);

    /**
     * 是否支持该用户类型
     * @param userType 用户类型
     * @return 是否支持
     */
    boolean supports(String userType);
} 