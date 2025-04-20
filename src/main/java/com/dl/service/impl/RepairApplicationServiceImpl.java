package com.dl.service.impl;

import com.dl.config.UploadConfig;
import com.dl.entity.dto.RepairApplicationDTO;
import com.dl.mapper.RepairApplicationMapper;
import com.dl.service.RepairApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Service
public class RepairApplicationServiceImpl implements RepairApplicationService {

    @Autowired
    private UploadConfig uploadConfig;
    
    @Autowired
    private RepairApplicationMapper repairApplicationMapper;
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public String uploadImage(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("上传的文件不能为空");
        }
        
        // 获取文件后缀
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        
        // 生成新的文件名
        String fileName = UUID.randomUUID().toString() + suffix;
        
        // 构建文件保存路径
        String datePath = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String dirPath = uploadConfig.getImagePath() + "/" + datePath;
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        // 文件完整路径
        String fullPath = dirPath + "/" + fileName;
        
        // 保存文件
        try {
            file.transferTo(new File(fullPath));
        } catch (IOException e) {
            log.error("文件上传失败", e);
            throw new RuntimeException("文件上传失败");
        }
        
        // 返回文件存储的绝对路径，而不是访问URL
        return fullPath;
    }

    @Override
    public String submitRepairApplication(RepairApplicationDTO applicationDTO) {
        log.info("提交报修申请, 申请信息: {}", applicationDTO);
        
        // 生成报修申请ID
        String repairId = generateRepairId();
        log.info("生成报修申请ID: {}", repairId);
        
        // 查询学生所属宿舍楼ID
        String buildId = repairApplicationMapper.getBuildIdByStudentId(applicationDTO.getStudentId());
        if (buildId == null || buildId.isEmpty()) {
            log.error("未找到学生所属宿舍楼信息, 学生ID: {}", applicationDTO.getStudentId());
            throw new RuntimeException("未找到学生所属宿舍楼信息");
        }
        
        // 查询宿管ID
        String hmId = repairApplicationMapper.getHmIdByBuildId(buildId);
        if (hmId == null || hmId.isEmpty()) {
            log.error("未找到宿舍楼管理员信息, 宿舍楼ID: {}", buildId);
            throw new RuntimeException("未找到宿舍楼管理员信息");
        }
        
        // 获取当前时间作为创建时间
        String createTime = LocalDateTime.now().format(DATETIME_FORMATTER);
        
        // 插入报修申请记录
        int result = repairApplicationMapper.insertRepairApplication(
                repairId,
                applicationDTO.getStudentId(),
                applicationDTO.getRepairType(),
                applicationDTO.getRoomId(),
                applicationDTO.getPublicArea(),
                applicationDTO.getItemName(),
                applicationDTO.getDescription(),
                applicationDTO.getImages(),
                hmId,
                createTime
        );
        
        if (result > 0) {
            log.info("报修申请提交成功, ID: {}", repairId);
            return repairId;
        } else {
            log.error("报修申请提交失败");
            throw new RuntimeException("报修申请提交失败");
        }
    }
    
    /**
     * 生成报修申请ID
     * 格式：RPyyyyMMddxxx
     * @return 报修申请ID
     */
    private String generateRepairId() {
        // 获取当前日期作为前缀
        String datePrefix = "RP" + LocalDateTime.now().format(DATE_FORMATTER);
        
        // 查询当天最大的报修申请ID
        String maxId = repairApplicationMapper.getMaxRepairIdByDate(datePrefix);
        
        // 如果当天没有报修申请，则序号从001开始
        if (maxId == null || maxId.isEmpty()) {
            return datePrefix + "001";
        }
        
        // 否则，序号+1
        int sequence = Integer.parseInt(maxId.substring(maxId.length() - 3)) + 1;
        return String.format("%s%03d", datePrefix, sequence);
    }
} 