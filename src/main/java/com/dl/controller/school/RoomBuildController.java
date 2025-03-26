package com.dl.controller.school;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.common.Result;
import com.dl.entity.dto.RoomBuildAddDTO;
import com.dl.entity.dto.RoomBuildQueryDTO;
import com.dl.entity.dto.RoomBuildUpdateDTO;
import com.dl.entity.vo.RoomBuildVO;
import com.dl.entity.vo.RoomBuildStatusResultVO;
import com.dl.service.RoomBuildService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

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

    @DeleteMapping("/delete/{buildId}")
    @ApiOperation("删除宿舍楼")
    public Result<Boolean> deleteRoomBuild(@PathVariable("buildId") String buildId) {
        boolean result = roomBuildService.deleteRoomBuild(buildId);
        if (result) {
            return Result.success(true);
        } else {
            return Result.error("删除宿舍楼失败");
        }
    }

    @PutMapping("/status")
    @ApiOperation("更新宿舍楼状态")
    public Result<Boolean> updateRoomBuildStatus(
            @ApiParam(value = "宿舍楼ID和使用状态", required = true) @RequestBody Map<String, String> requestBody) {
        
        String buildId = requestBody.get("buildId");
        String isUsed = requestBody.get("isUsed");
        
        if (buildId == null || buildId.isEmpty()) {
            return Result.error("宿舍楼ID不能为空");
        }
        
        if (isUsed == null || isUsed.isEmpty()) {
            return Result.error("使用状态不能为空");
        }
        
        RoomBuildStatusResultVO result = roomBuildService.updateRoomBuildStatus(buildId, isUsed);
        
        if (result.isSuccess()) {
            return Result.success(true);
        } else {
            return Result.error(result.getReason());
        }
    }

    @PutMapping("/update")
    @ApiOperation("修改宿舍楼信息")
    public Result<Boolean> updateRoomBuild(@RequestBody @Validated RoomBuildUpdateDTO updateDTO) {
        boolean result = roomBuildService.updateRoomBuild(updateDTO);
        if (result) {
            return Result.success(true);
        } else {
            return Result.error("修改宿舍楼信息失败");
        }
    }
} 