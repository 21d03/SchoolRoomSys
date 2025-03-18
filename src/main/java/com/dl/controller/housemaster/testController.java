package com.dl.controller.housemaster;

import com.dl.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
@Slf4j
@Api(tags = "测试")
public class testController {

    @SuppressWarnings("rawtypes")
    @GetMapping("/test1")
    @ApiOperation("测试")
    public Result test(){
        log.info("start");
        return Result.success("hello world!");
    }
}
