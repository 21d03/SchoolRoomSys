package com.dl.controller.school;

import com.dl.common.Result;
import com.dl.entity.pojo.CollegeInfo;
import com.dl.entity.vo.CollegeVO;
import com.dl.entity.vo.CollegeStudentDistributionVO;
import com.dl.service.CollegeService;
import com.dl.service.ICollegeInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 院系管理 控制器
 * </p>
 *
 * @author dongliang
 * @since 2024-11-01
 */
@RestController
@RequestMapping("/school/college")
@Api(tags = "学校管理-院系管理接口")
@Slf4j
public class CollegeController {
    
    @Resource
    private ICollegeInfoService collegeInfoService;

    @Autowired
    private CollegeService collegeService;
    
    @GetMapping("/list")
    @ApiOperation("查询全部院系信息")
    public Result<List<CollegeInfo>> listAllColleges() {
        List<CollegeInfo> collegeList = collegeInfoService.list();
        return Result.success(collegeList);
    }

    @GetMapping("/list2")
    @ApiOperation("获取所有学院列表")
    public Result<List<CollegeVO>> getAllColleges() {
        return Result.success(collegeService.getAllColleges());
    }

    @GetMapping("/student-distribution")
    @ApiOperation("获取各学院学生分布")
    public Result<List<CollegeStudentDistributionVO>> getCollegeStudentDistribution() {
        return Result.success(collegeService.getCollegeStudentDistribution());
    }
} 