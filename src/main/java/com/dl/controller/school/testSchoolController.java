package com.dl.controller.school;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dongliang
 * @date 2024/09/22 22:25:25
 * @description
 **/
@RestController
@RequestMapping("/school")
@Slf4j
@Api(tags = "测试登录校验拦截")
public class testSchoolController {

    @GetMapping("/test")
    public String test1(){

        return "hello world!";
    }
}
