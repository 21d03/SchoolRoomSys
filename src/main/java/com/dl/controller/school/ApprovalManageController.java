package com.dl.controller.school;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.LeaveApprovalQueryDTO;
import com.dl.entity.dto.RepairApprovalQueryDTO;
import com.dl.entity.vo.ApprovalCountVO;
import com.dl.entity.vo.LeaveApprovalVO;
import com.dl.entity.vo.RepairApprovalVO;
import com.dl.result.Result;
import com.dl.service.ApprovalManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school/approval")
@Api(tags = "审批管理")
public class ApprovalManageController {

    @Autowired
    private ApprovalManageService approvalManageService;

    @GetMapping("/count")
    @ApiOperation("查询审批数量统计")
    public Result<ApprovalCountVO> getApprovalCount() {
        return Result.success(approvalManageService.getApprovalCount());
    }

    @PostMapping("/leave/page")
    @ApiOperation("分页查询请假审批列表")
    public Result<IPage<LeaveApprovalVO>> queryLeaveApprovalPage(@RequestBody LeaveApprovalQueryDTO queryDTO) {
        return Result.success(approvalManageService.queryLeaveApprovalPage(queryDTO));
    }

    @PostMapping("/repair/page")
    @ApiOperation("分页查询报修审批列表")
    public Result<IPage<RepairApprovalVO>> queryRepairApprovalPage(@RequestBody RepairApprovalQueryDTO queryDTO) {
        return Result.success(approvalManageService.queryRepairApprovalPage(queryDTO));
    }
}