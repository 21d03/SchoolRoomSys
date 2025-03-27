package com.dl.controller.school;

import com.dl.entity.dto.TeacherQueryDTO;
import com.dl.entity.vo.TeacherVO;
import com.dl.result.Result;
import com.dl.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 辅导员信息控制器
 */
@RestController
@RequestMapping("/school/teacher")
@Api(tags = "辅导员管理相关接口")
@Slf4j
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    /**
     * 查询辅导员列表
     * @param queryDTO 查询参数
     * @return 辅导员列表
     */
    @PostMapping("/list")
    @ApiOperation("查询辅导员列表")
    public Result<List<TeacherVO>> queryTeacherList(@RequestBody TeacherQueryDTO queryDTO) {
        log.info("查询辅导员列表: {}", queryDTO);
        List<TeacherVO> teacherList = teacherService.queryTeacherList(queryDTO);
        return Result.success(teacherList);
    }
} 