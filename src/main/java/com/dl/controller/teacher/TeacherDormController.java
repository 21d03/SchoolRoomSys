package com.dl.controller.teacher;

import com.dl.entity.dto.DormStudentQueryDTO;
import com.dl.entity.dto.TeacherDormQueryDTO;
import com.dl.entity.vo.DormInfoVO;
import com.dl.entity.vo.TeacherDormVO;
import com.dl.result.Result;
import com.dl.service.TeacherDormService;
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
 * 教师宿舍查询控制器
 */
@RestController
@RequestMapping("/api/teacher/dorm")
@Api(tags = "教师宿舍查询接口")
@Slf4j
public class TeacherDormController {
    
    @Resource
    private TeacherDormService teacherDormService;
    
    /**
     * 查询教师管理的学生所住的宿舍
     * @param queryDTO 查询参数
     * @return 宿舍列表
     */
    @PostMapping("/list")
    @ApiOperation("查询教师管理的学生所住的宿舍")
    public Result<List<TeacherDormVO>> queryTeacherDorms(@RequestBody TeacherDormQueryDTO queryDTO) {
        log.info("查询教师管理的学生所住的宿舍，参数：{}", queryDTO);
        List<TeacherDormVO> result = teacherDormService.queryTeacherDorms(queryDTO.getTeacherId());
        return Result.success(result);
    }
    
    /**
     * 查询宿舍学生信息
     * @param queryDTO 查询参数
     * @return 宿舍详细信息
     */
    @PostMapping("/students")
    @ApiOperation("查询宿舍学生信息")
    public Result<DormInfoVO> queryDormStudents(@RequestBody DormStudentQueryDTO queryDTO) {
        log.info("查询宿舍学生信息，参数：{}", queryDTO);
        DormInfoVO result = teacherDormService.queryDormStudents(queryDTO.getBuildId(), queryDTO.getRoomId());
        return Result.success(result);
    }
} 