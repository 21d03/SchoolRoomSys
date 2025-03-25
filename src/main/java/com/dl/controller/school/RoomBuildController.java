package com.dl.controller.school;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.common.Result;
import com.dl.entity.dto.RoomBuildAddDTO;
import com.dl.entity.dto.RoomBuildQueryDTO;
import com.dl.entity.vo.RoomBuildVO;
import com.dl.service.RoomBuildService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/school/room/build")
@Api(tags = "学校管理-宿舍楼管理接口")
@Slf4j
public class RoomBuildController {
    
    @Resource
    private RoomBuildService roomBuildService;
    
    @PostMapping("/page")
    @ApiOperation("分页查询宿舍楼信息")
    public Result<IPage<RoomBuildVO>> queryRoomBuildPage(@RequestBody RoomBuildQueryDTO queryDTO) {
        IPage<RoomBuildVO> pageResult = roomBuildService.queryRoomBuildPage(queryDTO);
        return Result.success(pageResult);
    }
    
    @PostMapping("/add")
    @ApiOperation("新增宿舍楼")
    public Result<Boolean> addRoomBuild(@RequestBody @Validated RoomBuildAddDTO addDTO) {
        boolean result = roomBuildService.addRoomBuild(addDTO);
        if (result) {
            return Result.success(true);
        } else {
            return Result.error("新增宿舍楼失败");
        }
    }
} 