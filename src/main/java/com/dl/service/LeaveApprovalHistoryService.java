package com.dl.service;

import com.dl.entity.dto.LeaveApprovalHistoryDTO;
import com.dl.entity.vo.LeaveApprovalHistoryVO;
import com.dl.result.PageResult;

/**
 * 请假审批历史查询服务接口
 */
public interface LeaveApprovalHistoryService {
    
    /**
     * 分页查询教师已审批的请假记录
     * @param queryDTO 查询条件
     * @return 分页结果
     */
    PageResult<LeaveApprovalHistoryVO> queryApprovalHistory(LeaveApprovalHistoryDTO queryDTO);
} 