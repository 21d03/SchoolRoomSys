package com.dl.controller.housemaster;

import com.dl.entity.dto.RoomDetailQueryDTO;
import com.dl.result.Result;
import com.dl.service.RoomDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 宿舍详情查询控制器
 */
@Api(tags = "宿管-宿舍详情查询接口")
@RestController
@RequestMapping("/hm/room")
@Slf4j
public class RoomDetailController {
    
    @Autowired
    private RoomDetailService roomDetailService;
    
    /**
     * 查询宿舍详细信息
     * @param dto 查询参数
     * @return 查询结果
     */
    @ApiOperation("查询宿舍详细信息")
    @GetMapping("/detail")
    public Result getRoomDetail(@Valid RoomDetailQueryDTO dto) {
        return roomDetailService.getRoomDetail(dto);
    }
} 