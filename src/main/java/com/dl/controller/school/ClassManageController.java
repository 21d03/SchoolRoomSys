package com.dl.controller.school;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.ClassAddDTO;
import com.dl.entity.dto.ClassQueryDTO;
import com.dl.entity.dto.ClassUpdateDTO;
import com.dl.entity.vo.ClassVO;
import com.dl.result.Result;
import com.dl.service.ClassManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school/class-manage")
@Api(tags = "班级管理")
public class ClassManageController {

    @Autowired
    private ClassManageService classManageService;

    @PostMapping("/page")
    @ApiOperation("分页查询班级列表")
    public Result<IPage<ClassVO>> queryClassPage(@RequestBody ClassQueryDTO queryDTO) {
        return Result.success(classManageService.queryClassPage(queryDTO));
    }

    @PostMapping("/add")
    @ApiOperation("新增班级")
    public Result<Boolean> addClass(@RequestBody ClassAddDTO addDTO) {
        boolean result = classManageService.addClass(addDTO);
        if (result) {
            return Result.success(true);
        } else {
            return Result.error("学院不存在或班级已存在");
        }
    }

    @PutMapping("/update")
    @ApiOperation("编辑班级")
    public Result<Boolean> updateClass(@RequestBody ClassUpdateDTO updateDTO) {
        boolean result = classManageService.updateClass(updateDTO);
        if (result) {
            return Result.success(true);
        } else {
            return Result.error("班级已存在");
        }
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除班级")
    public Result<Boolean> deleteClass(
            @ApiParam(value = "专业名称", required = true) @RequestParam String profession,
            @ApiParam(value = "班级名称", required = true) @RequestParam String className) {
        int result = classManageService.deleteClass(profession, className);
        switch (result) {
            case 0:
                return Result.success(true);
            case 1:
                return Result.error("该班级存在分管辅导员，不能删除");
            case 2:
                return Result.error("该班级存在学生，不能删除");
            default:
                return Result.error("删除失败");
        }
    }
}
