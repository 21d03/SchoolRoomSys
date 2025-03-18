package com.dl.controller.school;

import com.dl.entity.dto.SchoolLoginDTO;
import com.dl.result.Result;
import com.dl.service.SchoolLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author dongliang
 * @date 2024/09/22 20:36:36
 * @description 老师端的登录和登出
 **/
@RestController
@Slf4j
@Api(tags = "登录登出")
@RequestMapping("/school")
public class SchoolLoginController {

    @Resource
    private SchoolLoginService schoolLoginService;


    @PostMapping("/login")
    @ApiOperation("老师登录")
    public Result login(@RequestBody SchoolLoginDTO schoolLoginDTO){
        log.info("老师端登录:{}",schoolLoginDTO);
        return Result.success(schoolLoginService.login(schoolLoginDTO));

    }
}
