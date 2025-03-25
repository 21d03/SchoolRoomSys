package com.dl.controller.school;

import com.dl.common.Result;
import com.dl.entity.vo.UnassignedHouseMasterVO;
import com.dl.service.IHouseMasterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "宿管管理接口")
@RestController
@RequestMapping("/school/house-master")
@Slf4j
public class HouseMasterController {

    @Autowired
    private IHouseMasterService houseMasterService;

    @ApiOperation("获取未分配宿舍楼的宿管列表")
    @GetMapping("/unassigned")
    public Result<List<UnassignedHouseMasterVO>> getUnassignedHouseMasters() {
        List<UnassignedHouseMasterVO> houseMasters = houseMasterService.getUnassignedHouseMasters();
        return Result.success(houseMasters);
    }
} 