package com.dl.controller.school;

import com.dl.entity.vo.UnassignedHouseMasterVO;
import com.dl.result.Result;
import com.dl.service.HouseMasterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/school/house-master")
@Api(tags = "为分配宿舍楼的宿管管理")
public class HouseMasterUnassignedController {

    @Autowired
    private HouseMasterService houseMasterService;

    @GetMapping("/unassigned")
    @ApiOperation("查询未分配宿舍楼的宿管")
    public Result<List<UnassignedHouseMasterVO>> getUnassignedHouseMasters() {
        List<UnassignedHouseMasterVO> list = houseMasterService.getUnassignedHouseMasters();
        return Result.success(list);
    }
    
}
