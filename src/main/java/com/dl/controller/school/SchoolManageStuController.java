package com.dl.controller.school;

import com.dl.entity.dto.StudentInfoDTO;
import com.dl.result.Result;
import com.dl.service.SchoolManageStuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author dongliang
 * @date 2024/09/23 21:35:35
 * @description
 **/
@RestController
@RequestMapping("/school/manageStu")
@Slf4j
@Api(tags = "管理学生相关接口")
public class SchoolManageStuController {

    @Resource
    private SchoolManageStuService schoolManageStuService;

    /**
     * 分页查询学生信息列表
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分页查询学生")
    public Result pageStu(Integer pageIndex, Integer pageSize) {
        return Result.success(schoolManageStuService.pageQuery(pageIndex,pageSize));
    }

    /**
     * 添加学生信息
     * @param studentInfoDto
     * @return
     */
    @PostMapping("/saveOne")
    @ApiOperation("添加学生信息")
    public Result saveOne(StudentInfoDTO studentInfoDto) {
        return Result.success(schoolManageStuService.saveOne(studentInfoDto));
    }
}
