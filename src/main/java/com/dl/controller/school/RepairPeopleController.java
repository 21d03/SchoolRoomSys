package com.dl.controller.school;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.RepairPeopleQueryDTO;
import com.dl.entity.vo.RepairPeopleVO;
import com.dl.result.Result;
import com.dl.service.RepairPeopleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school/repair/people")
@Api(tags = "维修人员管理")
public class RepairPeopleController {

    @Autowired
    private RepairPeopleService repairPeopleService;

    @PostMapping("/page")
    @ApiOperation("分页查询维修人员信息")
    public Result<IPage<RepairPeopleVO>> queryRepairPeoplePage(@RequestBody RepairPeopleQueryDTO queryDTO) {
        return Result.success(repairPeopleService.queryRepairPeoplePage(queryDTO));
    }
}
