package com.dl.controller.student;

import com.dl.entity.vo.StudentIndexVO;
import com.dl.result.Result;
import com.dl.service.StudentIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/student/index")
@Api(tags = "学生首页接口")
public class StudentIndexController {

    @Autowired
    private StudentIndexService studentIndexService;

    @GetMapping("/data")
    @ApiOperation("获取学生首页数据统计")
    public Result<StudentIndexVO> getStudentIndexData(
            @ApiParam(value = "学生ID", required = true)
            @RequestParam String studentId) {
        log.info("获取学生首页数据统计, 学生ID: {}", studentId);
        StudentIndexVO indexVO = studentIndexService.getStudentIndexData(studentId);
        return Result.success(indexVO);
    }
} 