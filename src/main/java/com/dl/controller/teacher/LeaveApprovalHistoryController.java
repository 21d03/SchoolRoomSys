package com.dl.controller.teacher;

import com.dl.entity.dto.LeaveApprovalHistoryDTO;
import com.dl.entity.vo.LeaveApprovalHistoryVO;
import com.dl.result.PageResult;
import com.dl.result.Result;
import com.dl.service.LeaveApprovalHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 请假审批历史查询控制器
 */
@RestController
@RequestMapping("/api/teacher/leave")
@Api(tags = "教师请假审批历史接口")
@Slf4j
public class LeaveApprovalHistoryController {
    
    @Resource
    private LeaveApprovalHistoryService leaveApprovalHistoryService;
    
    /**
     * 查询请假审批历史记录
     * @param queryDTO 查询参数
     * @return 分页结果
     */
    @PostMapping("/history")
    @ApiOperation("查询请假审批历史记录")
    public Result<PageResult<LeaveApprovalHistoryVO>> queryApprovalHistory(@RequestBody LeaveApprovalHistoryDTO queryDTO) {
        log.info("请假审批历史查询请求，参数：{}", queryDTO);
        
        // 记录关键参数
        log.info("查询教师ID: {}", queryDTO.getTeacherId());
        log.info("审批状态: {}", queryDTO.getStatus());
        log.info("审批日期范围: {} - {}", 
                queryDTO.getSubmitStartDate(), 
                queryDTO.getSubmitEndDate());
        
        PageResult<LeaveApprovalHistoryVO> result = leaveApprovalHistoryService.queryApprovalHistory(queryDTO);
        log.info("查询结果总数: {}", result.getTotal());
        
        return Result.success(result);
    }
} 