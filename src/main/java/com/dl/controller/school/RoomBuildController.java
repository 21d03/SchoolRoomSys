package com.dl.controller.school;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.common.Result;
import com.dl.entity.dto.RoomBuildAddDTO;
import com.dl.entity.dto.RoomBuildQueryDTO;
import com.dl.entity.dto.RoomBuildUpdateDTO;
import com.dl.entity.dto.RoomQueryDTO;
import com.dl.entity.vo.RoomBuildVO;
import com.dl.entity.vo.RoomBuildStatusResultVO;
import com.dl.entity.vo.RoomVO;
import com.dl.entity.vo.RoomDetailVO;
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
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/room/page")
    @ApiOperation("分页查询房间列表")
    public Result<IPage<RoomVO>> queryRoomPage(@RequestBody RoomQueryDTO queryDTO) {
        IPage<RoomVO> pageResult = roomBuildService.queryRoomPage(queryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/room/detail")
    @ApiOperation("查询房间详情")
    public Result<RoomDetailVO> getRoomDetail(
            @ApiParam(value = "宿舍楼ID", required = true) @RequestParam String buildId,
            @ApiParam(value = "房间号", required = true) @RequestParam String roomId) {
        RoomDetailVO detail = roomBuildService.getRoomDetail(buildId, roomId);
        return Result.success(detail);
    }
    
    @PutMapping("/room/status")
    @ApiOperation("更新房间使用状态")
    public Result<Boolean> updateRoomStatus(
            @ApiParam(value = "宿舍楼ID", required = true) @RequestParam String buildId,
            @ApiParam(value = "房间号", required = true) @RequestParam String roomId,
            @ApiParam(value = "使用状态：1-正常使用，0-暂停使用", required = true) @RequestParam String status) {
        if (buildId == null || buildId.isEmpty()) {
            return Result.error("宿舍楼ID不能为空");
        }
        
        if (roomId == null || roomId.isEmpty()) {
            return Result.error("房间号不能为空");
        }
        
        if (status == null || status.isEmpty()) {
            return Result.error("使用状态不能为空");
        }
        
        try {
            boolean result = roomBuildService.updateRoomStatus(buildId, roomId, status);
            if (result) {
                return Result.success(true);
            } else {
                return Result.error("更新房间状态失败");
            }
        } catch (com.dl.common.exception.ServiceException e) {
            // 将具体的服务异常信息返回给前端
            return Result.error(e.getMessage());
        } catch (Exception e) {
            // 其他异常使用通用错误消息
            log.error("更新房间状态出现未知异常", e);
            return Result.error("更新房间状态失败：系统异常");
        }
    }
} 