package com.dl.controller.school;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.ClassInfoQueryDTO;
import com.dl.entity.vo.ClassInfoVO;
import com.dl.result.Result;
import com.dl.service.ClassInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 班级信息控制器
 */
@RestController
@RequestMapping("/school/class")
@Api(tags = "班级信息管理")
public class ClassInfoController {

    @Autowired
    private ClassInfoService classInfoService;

    /**
     * 分页查询班级信息
     */
    @PostMapping("/page")
    @ApiOperation("分页查询班级信息")
    public Result<IPage<ClassInfoVO>> queryClassInfoPage(@RequestBody ClassInfoQueryDTO queryDTO) {
        return Result.success(classInfoService.queryClassInfoPage(queryDTO));
    }
} 