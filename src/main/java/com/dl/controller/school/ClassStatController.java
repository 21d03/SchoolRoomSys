package com.dl.controller.school;

import com.dl.common.Result;
import com.dl.entity.vo.ClassStudentDistributionVO;
import com.dl.service.ClassStatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/school/class-stat")
@Api(tags = "班级统计")
public class ClassStatController {

    @Autowired
    private ClassStatService classStatService;

    @GetMapping("/student-distribution")
    @ApiOperation("获取班级人数分布数据")
    public Result<List<ClassStudentDistributionVO>> getClassStudentDistribution(
            @ApiParam(value = "学院ID，不传则返回所有学院的班级", required = false)
            @RequestParam(required = false) String collegeId) {
        return Result.success(classStatService.getClassStudentDistribution(collegeId));
    }
}
