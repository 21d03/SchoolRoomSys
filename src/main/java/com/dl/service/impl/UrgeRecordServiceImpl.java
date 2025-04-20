package com.dl.service.impl;

import com.dl.entity.dto.UrgeRecordDTO;
import com.dl.mapper.UrgeRecordMapper;
import com.dl.service.UrgeRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
public class UrgeRecordServiceImpl implements UrgeRecordService {

    @Autowired
    private UrgeRecordMapper urgeRecordMapper;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createUrgeRecord(UrgeRecordDTO urgeRecordDTO) {
        log.info("创建催促记录, 参数: {}", urgeRecordDTO);
        
        // 检查是否已经催促过
        int count = urgeRecordMapper.checkUrgeExists(
                urgeRecordDTO.getApprovalId(),
                urgeRecordDTO.getFromId(),
                urgeRecordDTO.getToId(),
                urgeRecordDTO.getUrgeType()
        );
        
        // 如果已经催促过，直接返回成功
        if (count > 0) {
            log.info("该用户已经催促过, approvalId: {}, fromId: {}, toId: {}, urgeType: {}",
                    urgeRecordDTO.getApprovalId(), urgeRecordDTO.getFromId(), 
                    urgeRecordDTO.getToId(), urgeRecordDTO.getUrgeType());
            return true;
        }
        
        // 生成催促记录ID
        String id = UUID.randomUUID().toString().replace("-", "");
        
        // 插入催促记录
        int result = urgeRecordMapper.insertUrgeRecord(
                id,
                urgeRecordDTO.getApprovalId(),
                urgeRecordDTO.getApprovalType(),
                urgeRecordDTO.getUrgeType(),
                urgeRecordDTO.getUrgeContent(),
                urgeRecordDTO.getFromId(),
                urgeRecordDTO.getToId()
        );
        
        if (result > 0) {
            log.info("催促记录创建成功, id: {}", id);
            return true;
        } else {
            log.error("催促记录创建失败");
            return false;
        }
    }
} 