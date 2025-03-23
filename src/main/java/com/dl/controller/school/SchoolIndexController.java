package com.dl.controller.school;

import com.dl.entity.vo.SchoolIndexVO;
import com.dl.result.Result;
import com.dl.service.SchoolIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/school")
@Api(tags = "学校管理员首页接口")
@Slf4j
public class SchoolIndexController {
    
    @Resource
    private SchoolIndexService schoolIndexService;
    
    @GetMapping("/index")
    @ApiOperation("获取首页统计数据")
    public Result<SchoolIndexVO> getIndexData() {
        log.info("获取首页统计数据");
        SchoolIndexVO data = schoolIndexService.getIndexData();
        return Result.success(data);
    }
} 