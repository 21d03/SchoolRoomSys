package com.dl.service.impl;

import com.dl.common.exception.BusinessException;
import com.dl.entity.dto.LoginDTO;
import com.dl.result.Result;
import com.dl.service.LoginService;
import com.dl.service.strategy.LoginStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    
    @Resource
    private List<LoginStrategy<?>> loginStrategies;

    @Override
    public Result<?> login(LoginDTO loginDTO) {
        try {
            // 根据userType选择对应的策略
            LoginStrategy<?> strategy = loginStrategies.stream()
                    .filter(s -> s.supports(loginDTO.getUserType()))
                    .findFirst()
                    .orElseThrow(() -> new BusinessException("不支持的用户类型"));

            // 执行登录
            Object loginVO = strategy.login(loginDTO);
            return Result.success(loginVO);
        } catch (BusinessException e) {
            log.error("登录失败：{}", e.getMessage());
            return Result.error(e.getMessage());
        } catch (Exception e) {
            log.error("登录异常：", e);
            return Result.error("系统错误，请联系管理员");
        }
    }
} 