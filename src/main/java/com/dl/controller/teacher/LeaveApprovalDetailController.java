package com.dl.controller.teacher;

import com.dl.entity.dto.LeaveApprovalDetailDTO;
import com.dl.entity.vo.LeaveApprovalDetailVO;
import com.dl.result.Result;
import com.dl.service.LeaveApprovalDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 请假审批详情查询控制器
 */
@RestController
@RequestMapping("/api/teacher/leave")
@Api(tags = "教师请假审批详情接口")
@Slf4j
public class LeaveApprovalDetailController {
    
    @Resource
    private LeaveApprovalDetailService leaveApprovalDetailService;
    
    /**
     * 查询请假审批详情
     * @param queryDTO 查询参数
     * @return 请假审批详情
     */
    @PostMapping("/detail")
    @ApiOperation("查询请假审批详情")
    public Result<LeaveApprovalDetailVO> queryLeaveApprovalDetail(@RequestBody LeaveApprovalDetailDTO queryDTO) {
        log.info("查询请假审批详情，参数：{}", queryDTO);
        LeaveApprovalDetailVO result = leaveApprovalDetailService.queryLeaveApprovalDetail(queryDTO);
        return Result.success(result);
    }
} 