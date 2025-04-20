package com.dl.controller;

import com.dl.entity.dto.UrgeRecordDTO;
import com.dl.result.Result;
import com.dl.service.UrgeRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/urge")
@Api(tags = "催促接口")
public class UrgeRecordController {

    @Autowired
    private UrgeRecordService urgeRecordService;

    @PostMapping("/create")
    @ApiOperation("创建催促记录")
    public Result<Boolean> createUrgeRecord(@RequestBody UrgeRecordDTO urgeRecordDTO) {
        log.info("创建催促记录, 参数: {}", urgeRecordDTO);
        boolean success = urgeRecordService.createUrgeRecord(urgeRecordDTO);
        return Result.success(success);
    }
} 