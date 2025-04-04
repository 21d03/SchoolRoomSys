package com.dl.controller.teacher;

import com.dl.entity.dto.TeacherProfessionQueryDTO;
import com.dl.entity.vo.ClassNameVO;
import com.dl.entity.vo.ProfessionVO;
import com.dl.result.Result;
import com.dl.service.TeacherProfessionService;
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
 * 教师专业班级查询控制器
 */
@RestController
@RequestMapping("/api/teacher/profession")
@Api(tags = "教师专业班级查询接口")
@Slf4j
public class TeacherProfessionController {
    
    @Resource
    private TeacherProfessionService teacherProfessionService;
    
    /**
     * 查询教师管理的专业和班级
     * @param queryDTO 查询参数
     * @return 如果只传教师ID，返回专业列表；如果同时传专业名称，返回班级列表
     */
    @PostMapping("/query")
    @ApiOperation("查询教师管理的专业和班级")
    public Result<Object> queryProfessionAndClass(@RequestBody TeacherProfessionQueryDTO queryDTO) {
        log.info("查询教师管理的专业和班级，参数：{}", queryDTO);
        Object result = teacherProfessionService.queryProfessionAndClass(queryDTO);
        return Result.success(result);
    }
} 