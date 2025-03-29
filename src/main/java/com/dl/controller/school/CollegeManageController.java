package com.dl.controller.school;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.CollegeQueryDTO;
import com.dl.entity.vo.CollegeVO;
import com.dl.result.Result;
import com.dl.service.CollegeManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school/college")
@Api(tags = "学院管理")
public class CollegeManageController {

    @Autowired
    private CollegeManageService collegeManageService;

    @PostMapping("/page")
    @ApiOperation("分页查询学院列表")
    public Result<IPage<CollegeVO>> queryCollegePage(@RequestBody CollegeQueryDTO queryDTO) {
        return Result.success(collegeManageService.queryCollegePage(queryDTO));
    }

    @PostMapping("/add")
    @ApiOperation("新增学院")
    public Result<Boolean> addCollege(
            @ApiParam(value = "学院ID", required = true) @RequestParam String collegeId,
            @ApiParam(value = "学院名称", required = true) @RequestParam String collegeName) {
        boolean result = collegeManageService.addCollege(collegeId, collegeName);
        if (result) {
            return Result.success(true);
        } else {
            return Result.error("学院ID或名称已存在");
        }
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除学院")
    public Result<Boolean> deleteCollege(
            @ApiParam(value = "学院ID", required = true) @RequestParam String collegeId,
            @ApiParam(value = "学院名称", required = true) @RequestParam String collegeName) {
        int result = collegeManageService.deleteCollege(collegeId, collegeName);
        switch (result) {
            case 0:
                return Result.success(true);
            case 1:
                return Result.error("当前学院有学生，不能删除");
            case 2:
                return Result.error("当前学院有辅导员，不能删除");
            default:
                return Result.error("删除失败");
        }
    }
}
