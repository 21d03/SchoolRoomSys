package com.dl.controller.school;

import com.dl.common.Result;
import com.dl.entity.pojo.CollegeInfo;
import com.dl.service.ICollegeInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
    
    @GetMapping("/list")
    @ApiOperation("查询全部院系信息")
    public Result<List<CollegeInfo>> listAllColleges() {
        List<CollegeInfo> collegeList = collegeInfoService.list();
        return Result.success(collegeList);
    }
    
} 