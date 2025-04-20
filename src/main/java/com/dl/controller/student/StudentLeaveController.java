package com.dl.controller.student;

import com.dl.entity.dto.LeaveApplicationDTO;
import com.dl.result.Result;
import com.dl.service.LeaveApplicationService;
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
@RequestMapping("/student/leave")
@Api(tags = "学生请假接口")
public class StudentLeaveController {

    @Autowired
    private LeaveApplicationService leaveApplicationService;

    @PostMapping("/apply")
    @ApiOperation("提交请假申请")
    public Result<String> submitLeaveApplication(@RequestBody LeaveApplicationDTO applicationDTO) {
        log.info("提交请假申请, 申请信息: {}", applicationDTO);
        String leaveId = leaveApplicationService.submitLeaveApplication(applicationDTO);
        return Result.success(leaveId);
    }
} 