package com.dl.controller.school;

import com.dl.entity.vo.DormResourceOverviewVO;
import com.dl.entity.vo.BuildingRoomDistributionVO;
import com.dl.entity.vo.BuildingUsageRateVO;
import com.dl.entity.vo.RoomTypeDistributionVO;
import com.dl.result.Result;
import com.dl.service.DormResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/building/room-distribution")
    @ApiOperation("获取各宿舍楼房间分布情况")
    public Result<List<BuildingRoomDistributionVO>> getBuildingRoomDistribution() {
        return Result.success(dormResourceService.getBuildingRoomDistribution());
    }

    @GetMapping("/building/usage-rate")
    @ApiOperation("获取各宿舍楼使用率")
    public Result<List<BuildingUsageRateVO>> getBuildingUsageRate() {
        return Result.success(dormResourceService.getBuildingUsageRate());
    }

    @GetMapping("/room-type-distribution")
    @ApiOperation("获取房间类型分布情况")
    public Result<List<RoomTypeDistributionVO>> getRoomTypeDistribution() {
        return Result.success(dormResourceService.getRoomTypeDistribution());
    }
}
