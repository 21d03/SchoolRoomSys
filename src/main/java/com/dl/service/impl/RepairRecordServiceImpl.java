package com.dl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.dto.RepairRecordQueryDTO;
import com.dl.entity.vo.RepairRecordVO;
import com.dl.mapper.RepairRecordMapper;
import com.dl.service.RepairRecordService;
import com.dl.utils.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Service
public class RepairRecordServiceImpl implements RepairRecordService {

    @Autowired
    private RepairRecordMapper repairRecordMapper;
    
    @Override
    public IPage<RepairRecordVO> queryRepairRecordPage(RepairRecordQueryDTO queryDTO) {
        log.info("查询学生报修记录, 查询条件: {}", queryDTO);
        
        Page<RepairRecordVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        
        // 查询记录
        IPage<RepairRecordVO> resultPage = repairRecordMapper.queryRepairRecordPage(
                page,
                queryDTO.getStudentId(),
                queryDTO.getRepairType(),
                queryDTO.getHmStatus(),
                queryDTO.getRpStatus(),
                queryDTO.getItemName()
        );
        
        // 处理图片转Base64
        if (resultPage != null && resultPage.getRecords() != null) {
            List<RepairRecordVO> records = resultPage.getRecords();
            for (RepairRecordVO record : records) {
                // 处理图片路径转Base64
                processImages(record);
            }
        }
        
        return resultPage;
    }
    
    /**
     * 处理图片路径转Base64
     * @param record 报修记录
     */
    private void processImages(RepairRecordVO record) {
        if (record != null && StringUtils.hasText(record.getImages())) {
            String[] imagePaths = record.getImages().split(",");
            StringBuilder base64Images = new StringBuilder();
            
            for (int i = 0; i < imagePaths.length; i++) {
                String imagePath = imagePaths[i].trim();
                // 将图片转为Base64
                String base64Image = ImageUtil.imageToBase64(imagePath);
                
                if (base64Image != null) {
                    if (i > 0) {
                        base64Images.append(",");
                    }
                    base64Images.append(base64Image);
                } else {
                    log.warn("图片转Base64失败: {}", imagePath);
                }
            }
            
            if (base64Images.length() > 0) {
                // 更新为Base64图片
                record.setImages(base64Images.toString());
            }
        }
    }
} 