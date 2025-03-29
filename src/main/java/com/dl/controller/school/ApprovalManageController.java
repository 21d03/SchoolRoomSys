package com.dl.controller.school;

import com.dl.entity.vo.ApprovalCountVO;
import com.dl.result.Result;
import com.dl.service.ApprovalManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}