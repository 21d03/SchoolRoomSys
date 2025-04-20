package com.dl.service;

import com.dl.entity.dto.RepairApplicationDTO;
import org.springframework.web.multipart.MultipartFile;

public interface RepairApplicationService {
    
    /**
     * 上传报修图片
     * @param file 图片文件
     * @return 图片访问URL
     */
    String uploadImage(MultipartFile file);
    
    /**
     * 提交报修申请
     * @param applicationDTO 报修申请信息
     * @return 报修申请ID
     */
    String submitRepairApplication(RepairApplicationDTO applicationDTO);
} 