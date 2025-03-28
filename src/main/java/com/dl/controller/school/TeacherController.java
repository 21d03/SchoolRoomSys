package com.dl.controller.school;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.TeacherQueryDTO;
import com.dl.entity.vo.TeacherVO;
import com.dl.result.Result;
import com.dl.service.TeacherService;
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
@RequestMapping("/school/teacher")
@Api(tags = "教师管理")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 查询教师列表
     */
    @PostMapping("/list")
    @ApiOperation("查询教师列表")
    public Result<IPage<TeacherVO>> queryTeacherPage(@RequestBody TeacherQueryDTO queryDTO) {
        return Result.success(teacherService.queryTeacherPage(queryDTO));
    }
} 