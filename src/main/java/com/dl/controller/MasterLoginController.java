package com.dl.controller;

import com.dl.entity.dto.LoginDTO;
import com.dl.result.Result;
import com.dl.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 宿管登录控制器
 */
@RestController
@RequestMapping("/master")
@Api(tags = "宿管登录相关接口")
@Slf4j
public class MasterLoginController {

    @Resource
    private LoginService loginService;

    /**
     * 宿管登录
     * @param loginDTO 登录参数
     * @return 登录结果
     */
    @PostMapping("/login")
    @ApiOperation("宿管登录")
    public Result<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        // 设置用户类型为宿管
        loginDTO.setUserType("4");
        log.info("宿管登录: {}", loginDTO.getUserId());
        return loginService.login(loginDTO);
    }
} 