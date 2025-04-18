package com.dl.controller;

import com.dl.entity.vo.RepairApprovalDetailVO;
import com.dl.result.Result;
import com.dl.service.RepairApprovalDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/repair")
@Api(tags = "报修审批单详情接口")
public class RepairApprovalDetailController {

    @Autowired
    private RepairApprovalDetailService repairApprovalDetailService;

    @GetMapping("/detail/{approvalId}")
    @ApiOperation("查询报修审批单详情")
    public Result<RepairApprovalDetailVO> getRepairApprovalDetail(
            @ApiParam(value = "报修审批单ID", required = true)
            @PathVariable String approvalId) {
        log.info("查询报修审批单详情, ID: {}", approvalId);
        RepairApprovalDetailVO detailVO = repairApprovalDetailService.getRepairApprovalDetail(approvalId);
        return Result.success(detailVO);
    }
} 