package com.dl.controller.student;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.LeaveRecordQueryDTO;
import com.dl.entity.vo.LeaveRecordVO;
import com.dl.result.Result;
import com.dl.service.LeaveRecordService;
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
public class StudentLeaveRecordController {

    @Autowired
    private LeaveRecordService leaveRecordService;

    @PostMapping("/records")
    @ApiOperation("查询学生请假记录")
    public Result<IPage<LeaveRecordVO>> queryLeaveRecords(@RequestBody LeaveRecordQueryDTO queryDTO) {
        log.info("查询学生请假记录, 查询条件: {}", queryDTO);
        IPage<LeaveRecordVO> page = leaveRecordService.queryLeaveRecordPage(queryDTO);
        return Result.success(page);
    }
} 