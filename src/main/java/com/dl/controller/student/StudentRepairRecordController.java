package com.dl.controller.student;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.RepairRecordQueryDTO;
import com.dl.entity.vo.RepairRecordVO;
import com.dl.result.Result;
import com.dl.service.RepairRecordService;
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
@RequestMapping("/student/repair")
@Api(tags = "学生报修接口")
public class StudentRepairRecordController {

    @Autowired
    private RepairRecordService repairRecordService;

    @PostMapping("/records")
    @ApiOperation("查询报修记录")
    public Result<IPage<RepairRecordVO>> queryRepairRecords(@RequestBody RepairRecordQueryDTO queryDTO) {
        log.info("查询报修记录, 查询条件: {}", queryDTO);
        IPage<RepairRecordVO> page = repairRecordService.queryRepairRecordPage(queryDTO);
        return Result.success(page);
    }
} 