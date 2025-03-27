package com.dl.controller.school;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.common.Result;
import com.dl.entity.dto.StudentQueryDTO;
import com.dl.entity.vo.StudentVO;
import com.dl.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 学生管理 控制器
 * </p>
 *
 * @author dongliang
 * @since 2024-11-01
 */
@RestController
@RequestMapping("/school/student")
@Api(tags = "学校管理-学生管理接口")
@Slf4j
public class StudentController {
    
    @Resource
    private StudentService studentService;
    
    @PostMapping("/page")
    @ApiOperation("分页查询学生信息")
    public Result<IPage<StudentVO>> queryStudentPage(@RequestBody StudentQueryDTO queryDTO) {
        IPage<StudentVO> pageResult = studentService.queryStudentPage(queryDTO);
        return Result.success(pageResult);
    }
    
} 