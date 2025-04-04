package com.dl.controller.teacher;

import com.dl.entity.dto.TeacherStudentQueryDTO;
import com.dl.entity.vo.StudentListVO;
import com.dl.result.PageResult;
import com.dl.result.Result;
import com.dl.service.TeacherStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 教师管理学生控制器
 */
@RestController
@RequestMapping("/api/teacher/student")
@Api(tags = "教师管理学生接口")
@Slf4j
public class TeacherStudentController {
    
    @Resource
    private TeacherStudentService teacherStudentService;
    
    /**
     * 教师查询学生列表
     * @param queryDTO 查询参数
     * @return 学生列表分页结果
     */
    @PostMapping("/list")
    @ApiOperation("教师查询学生列表")
    public Result<PageResult<StudentListVO>> queryStudentList(@RequestBody TeacherStudentQueryDTO queryDTO) {
        log.info("教师查询学生列表，参数：{}", queryDTO);
        PageResult<StudentListVO> result = teacherStudentService.queryStudentList(queryDTO);
        return Result.success(result);
    }
    
} 