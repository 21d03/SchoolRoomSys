package com.dl.controller.school;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
} 