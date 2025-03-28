package com.dl.controller.school;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.ClassInfoQueryDTO;
import com.dl.entity.dto.AssignClassDTO;
import com.dl.entity.dto.UnassignClassDTO;
import com.dl.entity.vo.ClassInfoVO;
import com.dl.entity.vo.UnassignedClassVO;
import com.dl.result.Result;
import com.dl.service.ClassInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/unassigned")
    @ApiOperation("查询未分配辅导员的班级")
    public Result<List<UnassignedClassVO>> queryUnassignedClasses(String collegeName) {
        List<UnassignedClassVO> list = classInfoService.queryUnassignedClasses(collegeName);
        return Result.success(list);
    }

    @PostMapping("/assign")
    @ApiOperation("分配班级辅导员")
    public Result<Boolean> assignClass(@RequestBody AssignClassDTO assignClassDTO) {
        boolean result = classInfoService.assignClass(assignClassDTO);
        if (result) {
            return Result.success(true);
        } else {
            return Result.error("分配班级失败");
        }
    }

    @PostMapping("/unassign")
    @ApiOperation("取消班级分管")
    public Result<Boolean> unassignClass(@RequestBody UnassignClassDTO unassignClassDTO) {
        boolean result = classInfoService.unassignClass(unassignClassDTO);
        if (result) {
            return Result.success(true);
        } else {
            return Result.error("取消分管失败");
        }
    }
} 