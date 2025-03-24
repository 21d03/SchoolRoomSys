package com.dl.controller.school;

import com.dl.common.Result;
import com.dl.entity.vo.RecentApprovalVO;
import com.dl.service.ApprovalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/school/approval")
@Api(tags = "学校管理-审批管理接口")
@Slf4j
public class SchoolApprovalController {
    
    @Resource
    private ApprovalService approvalService;
    
    @GetMapping("/recent")
    @ApiOperation("获取最近的审批记录")
    public Result<List<RecentApprovalVO>> getRecentApprovals() {
        List<RecentApprovalVO> approvals = approvalService.getRecentApprovals();
        return Result.success(approvals);
    }
} 