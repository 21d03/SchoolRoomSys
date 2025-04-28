package com.dl.service.impl;

import com.dl.entity.vo.RepairDetailVO;
import com.dl.mapper.RepairDetailMapper;
import com.dl.result.Result;
import com.dl.service.RepairDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 报修详情查询服务实现
 */
@Service
@Slf4j
public class RepairDetailServiceImpl implements RepairDetailService {
    
    @Autowired
    private RepairDetailMapper repairDetailMapper;
    
    @Value("${upload.imageUrlPrefix}")
    private String imageUrlPrefix;
    
    @Override
    public Result getRepairDetail(String repairId) {
        log.info("查询报修单详情，repairId: {}", repairId);
        
        try {
            // 查询报修详情
            RepairDetailVO repairDetail = repairDetailMapper.getRepairDetail(repairId);
            if (repairDetail == null) {
                return Result.error("报修单不存在");
            }
            
            // 处理图片，将路径转换为完整URL
            if (StringUtils.hasText(repairDetail.getImages())) {
                List<String> imageUrls = Arrays.stream(repairDetail.getImages().split(","))
                        .map(path -> imageUrlPrefix + "/" + path)
                        .collect(Collectors.toList());
                repairDetail.setImageBase64List(imageUrls);
            }
            
            return Result.success(repairDetail);
        } catch (Exception e) {
            log.error("查询报修单详情失败", e);
            return Result.error("查询报修单详情失败");
        }
    }
} 