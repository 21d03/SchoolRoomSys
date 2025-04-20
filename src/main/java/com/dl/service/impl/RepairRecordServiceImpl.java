package com.dl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.dto.RepairRecordQueryDTO;
import com.dl.entity.vo.RepairRecordVO;
import com.dl.mapper.RepairRecordMapper;
import com.dl.service.RepairRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RepairRecordServiceImpl implements RepairRecordService {

    @Autowired
    private RepairRecordMapper repairRecordMapper;
    
    @Override
    public IPage<RepairRecordVO> queryRepairRecordPage(RepairRecordQueryDTO queryDTO) {
        log.info("查询学生报修记录, 查询条件: {}", queryDTO);
        
        Page<RepairRecordVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        
        return repairRecordMapper.queryRepairRecordPage(
                page,
                queryDTO.getStudentId(),
                queryDTO.getRepairType(),
                queryDTO.getHmStatus(),
                queryDTO.getRpStatus(),
                queryDTO.getItemName()
        );
    }
} 