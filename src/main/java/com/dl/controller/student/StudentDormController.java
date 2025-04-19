package com.dl.controller.student;

import com.dl.entity.vo.StudentDormVO;
import com.dl.result.Result;
import com.dl.service.StudentDormService;
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
@RequestMapping("/student/dorm")
@Api(tags = "学生宿舍信息接口")
public class StudentDormController {

    @Autowired
    private StudentDormService studentDormService;

    @GetMapping("/info")
    @ApiOperation("查询学生宿舍信息")
    public Result<StudentDormVO> getStudentDormInfo(
            @ApiParam(value = "学生ID", required = true)
            @RequestParam String studentId) {
        log.info("查询学生宿舍信息, 学生ID: {}", studentId);
        StudentDormVO dormVO = studentDormService.getStudentDormInfo(studentId);
        return Result.success(dormVO);
    }
} 