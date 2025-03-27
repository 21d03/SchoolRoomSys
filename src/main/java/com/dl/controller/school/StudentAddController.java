package com.dl.controller.school;

import com.dl.entity.dto.StudentInfoDTO;
import com.dl.result.Result;
import com.dl.service.SchoolManageStuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学生添加控制器
 */
@RestController
@RequestMapping("/school/student")
@Api(tags = "学生管理相关接口")
@Slf4j
public class StudentAddController {

    @Autowired
    private SchoolManageStuService schoolManageStuService;

    /**
     * 添加新学生
     * @param studentInfoDto 学生信息
     * @return 添加结果
     */
    @PostMapping("/add")
    @ApiOperation("添加新学生")
    public Result addStudent(@RequestBody StudentInfoDTO studentInfoDto) {
        log.info("添加新学生: {}", studentInfoDto);
        return schoolManageStuService.saveOne(studentInfoDto);
    }
} 