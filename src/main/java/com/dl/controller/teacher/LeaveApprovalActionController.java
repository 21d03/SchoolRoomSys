package com.dl.controller.teacher;

import com.dl.entity.dto.LeaveApprovalActionDTO;
import com.dl.result.Result;
import com.dl.service.LeaveApprovalActionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 请假审批操作控制器
 */
@RestController
@RequestMapping("/api/teacher/leave")
@Api(tags = "教师请假审批操作接口")
@Slf4j
public class LeaveApprovalActionController {
    
    @Resource
    private LeaveApprovalActionService leaveApprovalActionService;
    
    /**
     * 处理请假审批
     * @param actionDTO 审批操作参数
     * @return 处理结果
     */
    @PostMapping("/approve")
    @ApiOperation("处理请假审批")
    public Result<Boolean> handleApproval(@RequestBody LeaveApprovalActionDTO actionDTO) {
        log.info("处理请假审批，参数：{}", actionDTO);
        boolean success = leaveApprovalActionService.handleApproval(actionDTO);
        
        if (success) {
            return Result.success(true);
        } else {
            return Result.error("审批处理失败，请确认审批参数或权限是否正确");
        }
    }
} 