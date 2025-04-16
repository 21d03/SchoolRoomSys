package com.dl.controller.teacher;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.TeacherRepairApprovalQueryDTO;
import com.dl.entity.vo.TeacherRepairApprovalVO;
import com.dl.result.Result;
import com.dl.service.TeacherRepairApprovalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/teacher/repair")
@Api(tags = "教师报修管理接口")
public class TeacherRepairApprovalController {

    @Autowired
    private TeacherRepairApprovalService teacherRepairApprovalService;

    @PostMapping("/query")
    @ApiOperation("查询教师管理的学生报修记录")
    public Result<IPage<TeacherRepairApprovalVO>> queryTeacherRepairApprovalPage(@RequestBody TeacherRepairApprovalQueryDTO queryDTO) {
        log.info("查询教师管理的学生报修记录, 参数: {}", queryDTO);
        return Result.success(teacherRepairApprovalService.queryTeacherRepairApprovalPage(queryDTO));
    }
} 