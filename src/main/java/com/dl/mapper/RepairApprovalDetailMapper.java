package com.dl.mapper;

import com.dl.entity.vo.RepairApprovalDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RepairApprovalDetailMapper {
    
    /**
     *
     * 根据报修审批单ID查询详情
     * @param approvalId 报修审批单ID
     * @return 报修审批单详情
     */
    RepairApprovalDetailVO getRepairApprovalDetail(@Param("approvalId") String approvalId);
} 