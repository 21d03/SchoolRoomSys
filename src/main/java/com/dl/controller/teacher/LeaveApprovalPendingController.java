package com.dl.controller.teacher;

import com.dl.entity.dto.LeaveApprovalPendingDTO;
import com.dl.entity.vo.LeaveApprovalPendingVO;
import com.dl.result.PageResult;
import com.dl.result.Result;
import com.dl.service.LeaveApprovalPendingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 请假待审批查询控制器
 */
@RestController
@RequestMapping("/api/teacher/leave/pending")
@Api(tags = "教师请假待审批接口")
@Slf4j
public class LeaveApprovalPendingController {
    
    @Resource
    private LeaveApprovalPendingService leaveApprovalPendingService;
    
    /**
     * 分页查询教师待审批的请假记录
     * @param queryDTO 查询条件
     * @return 分页结果
     */
    @PostMapping("/list")
    @ApiOperation("查询教师待审批的请假记录")
    public Result<PageResult<LeaveApprovalPendingVO>> queryPendingLeaveApprovals(@RequestBody LeaveApprovalPendingDTO queryDTO) {
        log.info("查询教师待审批的请假记录，参数：{}", queryDTO);
        PageResult<LeaveApprovalPendingVO> result = leaveApprovalPendingService.queryPendingLeaveApprovals(queryDTO);
        return Result.success(result);
    }
} 