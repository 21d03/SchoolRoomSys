package com.dl.controller.school;

import com.dl.entity.vo.PersonnelOverviewVO;
import com.dl.result.Result;
import com.dl.service.PersonnelStatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school/personnel")
@Api(tags = "人员管理")
public class PersonnelStatController {

    @Autowired
    private PersonnelStatService personnelStatService;

    @GetMapping("/overview")
    @ApiOperation("获取人员总览数据")
    public Result<PersonnelOverviewVO> getPersonnelOverview() {
        return Result.success(personnelStatService.getPersonnelOverview());
    }
}
