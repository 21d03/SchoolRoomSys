package com.dl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.LeaveRecordQueryDTO;
import com.dl.entity.vo.LeaveRecordVO;

public interface LeaveRecordService {
    
    /**
     * 查询学生请假记录
     * @param queryDTO 查询条件
     * @return 分页结果
     */
    IPage<LeaveRecordVO> queryLeaveRecordPage(LeaveRecordQueryDTO queryDTO);
} 