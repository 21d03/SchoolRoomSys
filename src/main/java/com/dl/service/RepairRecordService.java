package com.dl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.RepairRecordQueryDTO;
import com.dl.entity.vo.RepairRecordVO;

public interface RepairRecordService {
    
    /**
     * 查询学生报修记录
     * @param queryDTO 查询条件
     * @return 分页结果
     */
    IPage<RepairRecordVO> queryRepairRecordPage(RepairRecordQueryDTO queryDTO);
} 