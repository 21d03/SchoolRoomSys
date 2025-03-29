package com.dl.controller.school;

import com.dl.common.Result;
import com.dl.entity.vo.StudentGenderRatioVO;
import com.dl.service.StudentStatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school/student-stat")
@Api(tags = "学生统计")
public class StudentStatController {

    @Autowired
    private StudentStatService studentStatService;

    @GetMapping("/gender-ratio")
    @ApiOperation("获取学生性别比例")
    public Result<StudentGenderRatioVO> getStudentGenderRatio() {
        return Result.success(studentStatService.getStudentGenderRatio());
    }
}
