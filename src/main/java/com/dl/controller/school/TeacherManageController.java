package com.dl.controller.school;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.TeacherAddDTO;
import com.dl.entity.dto.TeacherManageQueryDTO;
import com.dl.entity.vo.TeacherManageVO;
import com.dl.result.Result;
import com.dl.service.TeacherManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 教师管理控制器
 */
@RestController
@RequestMapping("/school/teacher/manage")
@Api(tags = "教师管理")
public class TeacherManageController {

    @Autowired
    private TeacherManageService teacherManageService;

    /**
     * 分页查询教师管理信息
     */
    @PostMapping("/page")
    @ApiOperation("分页查询教师管理信息")
    public Result<IPage<TeacherManageVO>> queryTeacherManagePage(@RequestBody TeacherManageQueryDTO queryDTO) {
        return Result.success(teacherManageService.queryTeacherManagePage(queryDTO));
    }

    /**
     * 新增教师
     */
    @PostMapping("/add")
    @ApiOperation("新增教师")
    public Result<Boolean> addTeacher(@RequestBody TeacherAddDTO addDTO) {
        return Result.success(teacherManageService.addTeacher(addDTO));
    }

    @GetMapping("/college/{teacherId}")
    @ApiOperation("根据教师ID查询所属学院")
    public Result<String> getCollegeNameByTeacherId(@PathVariable String teacherId) {
        String collegeName = teacherManageService.getCollegeNameByLevel(teacherId);
        return Result.success(collegeName);
    }
} 