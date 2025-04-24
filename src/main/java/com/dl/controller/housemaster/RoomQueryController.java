package com.dl.controller.housemaster;

import com.dl.entity.dto.RoomQueryDTO;
import com.dl.result.Result;
import com.dl.service.RoomQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 宿舍查询控制器
 */
@Api(tags = "宿管-宿舍查询相关接口")
@RestController
@RequestMapping("/hm/room")
@Slf4j
public class RoomQueryController {
    
    @Autowired
    private RoomQueryService roomQueryService;
    
    /**
     * 查询宿舍信息
     * @param dto 查询参数
     * @return 查询结果
     */
    @ApiOperation("查询宿舍信息")
    @GetMapping("/query")
    public Result queryRooms(@Valid RoomQueryDTO dto) {
        return roomQueryService.queryRooms(dto);
    }
} 