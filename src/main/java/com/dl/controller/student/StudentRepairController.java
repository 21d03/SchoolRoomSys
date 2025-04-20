package com.dl.controller.student;

import com.dl.entity.dto.RepairApplicationDTO;
import com.dl.result.Result;
import com.dl.service.RepairApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/student/repair")
@Api(tags = "学生报修接口")
public class StudentRepairController {

    @Autowired
    private RepairApplicationService repairApplicationService;

    @PostMapping("/upload")
    @ApiOperation("上传报修图片")
    public Result<String> uploadImage(@RequestParam("file") MultipartFile file) {
        log.info("上传报修图片, 文件名: {}", file.getOriginalFilename());
        String imageUrl = repairApplicationService.uploadImage(file);
        return Result.success(imageUrl);
    }

    @PostMapping("/apply")
    @ApiOperation("提交报修申请")
    public Result<String> submitRepairApplication(@RequestBody RepairApplicationDTO applicationDTO) {
        log.info("提交报修申请, 申请信息: {}", applicationDTO);
        String repairId = repairApplicationService.submitRepairApplication(applicationDTO);
        return Result.success(repairId);
    }
} 