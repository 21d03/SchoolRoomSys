package com.dl.controller.school;

import com.dl.entity.vo.DormResourceOverviewVO;
import com.dl.result.Result;
import com.dl.service.DormResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school/dorm")
@Api(tags = "宿舍资源管理")
public class DormResourceController {

    @Autowired
    private DormResourceService dormResourceService;

    @GetMapping("/overview")
    @ApiOperation("获取宿舍资源总览")
    public Result<DormResourceOverviewVO> getDormResourceOverview() {
        return Result.success(dormResourceService.getDormResourceOverview());
    }
}
