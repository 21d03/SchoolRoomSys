package com.dl.controller.housemaster;

import com.dl.result.Result;
import com.dl.service.RepairApprovalQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 报修单查询控制器
 */
@Api(tags = "宿管-报修单查询接口")
@RestController
@RequestMapping("/hm/repair")
@Slf4j
public class RepairApprovalQueryController {
    
    @Autowired
    private RepairApprovalQueryService repairApprovalQueryService;
    
    /**
     * 查询宿管用户的报修单列表
     * @param hmId 宿管ID
     * @return 报修单列表
     */
    @ApiOperation("查询报修单列表")
    @GetMapping("/list")
    public Result getRepairApprovalList(
            @ApiParam(value = "宿管ID", required = true)
            @RequestParam("hmId") String hmId) {
        return repairApprovalQueryService.getRepairApprovalList(hmId);
    }
} 