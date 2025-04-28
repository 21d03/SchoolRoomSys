package com.dl.controller.housemaster;

import com.dl.result.Result;
import com.dl.service.RepairDetailService;
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
 * 报修详情查询控制器
 */
@Api(tags = "宿管-报修详情查询接口")
@RestController
@RequestMapping("/hm/repair")
@Slf4j
public class RepairDetailController {
    
    @Autowired
    private RepairDetailService repairDetailService;
    
    /**
     * 查询报修单详情
     * @param repairId 报修单ID
     * @return 报修单详情
     */
    @ApiOperation("查询报修单详情")
    @GetMapping("/detail")
    public Result getRepairDetail(
            @ApiParam(value = "报修单ID", required = true)
            @RequestParam("repairId") String repairId) {
        return repairDetailService.getRepairDetail(repairId);
    }
} 