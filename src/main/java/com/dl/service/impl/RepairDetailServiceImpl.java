package com.dl.service.impl;

import com.dl.entity.vo.RepairDetailVO;
import com.dl.mapper.RepairDetailMapper;
import com.dl.result.Result;
import com.dl.service.RepairDetailService;
import com.dl.utils.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报修详情查询服务实现
 */
@Service
@Slf4j
public class RepairDetailServiceImpl implements RepairDetailService {
    
    @Autowired
    private RepairDetailMapper repairDetailMapper;
    
    @Autowired
    private ImageUtil imageUtil;
    
    @Override
    public Result getRepairDetail(String repairId) {
        log.info("查询报修单详情，repairId: {}", repairId);
        
        try {
            // 查询报修详情
            RepairDetailVO repairDetail = repairDetailMapper.getRepairDetail(repairId);
            if (repairDetail == null) {
                return Result.error("报修单不存在");
            }
            
            // 处理图片，将路径转换为Base64编码
            if (repairDetail.getImages() != null && !repairDetail.getImages().isEmpty()) {
                List<String> imageBase64List = imageUtil.getBase64Images(repairDetail.getImages());
                repairDetail.setImageBase64List(imageBase64List);
            }
            
            return Result.success(repairDetail);
        } catch (Exception e) {
            log.error("查询报修单详情失败", e);
            return Result.error("查询报修单详情失败");
        }
    }
} 