package com.dl.controller;

import com.dl.common.annotation.OperationLog;
import com.dl.entity.dto.LoginDTO;
import com.dl.result.Result;
import com.dl.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
@Api(tags = "登录接口")
@Slf4j
public class LoginController {
    
    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    @ApiOperation("统一登录接口")
    @OperationLog(value = "用户登录", operationType = OperationLog.OperationType.LOGIN)
    public Result<?> login(@Validated @RequestBody LoginDTO loginDTO) {
        log.info("用户登录：{}", loginDTO);
        return loginService.login(loginDTO);
    }
} 