package com.dl.service;

import com.dl.entity.dto.LoginDTO;
import com.dl.result.Result;

public interface LoginService {
    /**
     * 统一登录接口
     * @param loginDTO 登录参数
     * @return 登录结果
     */
    Result<?> login(LoginDTO loginDTO);
} 