package com.dl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ApprovalManageMapper {

    /**
     * 查询请假审批总数
     */
    @Select("SELECT COUNT(*) FROM leave_approval")
    Integer getLeaveApprovalTotalCount();

    /**
     * 查询请假待处理审批数量
     */
    @Select("SELECT COUNT(*) FROM leave_approval WHERE status = '0'")
    Integer getLeaveApprovalPendingCount();

    /**
     * 查询请假已审批数量
     */
    @Select("SELECT COUNT(*) FROM leave_approval WHERE status = '1'")
    Integer getLeaveApprovalApprovedCount();

    /**
     * 查询请假已拒绝数量
     */
    @Select("SELECT COUNT(*) FROM leave_approval WHERE status = '2'")
    Integer getLeaveApprovalRejectedCount();
    
    /**
     * 查询报修审批总数
     */
    @Select("SELECT COUNT(*) FROM repair_approval")
    Integer getRepairApprovalTotalCount();

    /**
     * 查询报修待处理审批数量
     */
    @Select("SELECT COUNT(*) FROM repair_approval WHERE hm_status = '0' OR (hm_status = '1' AND rp_status = '0')")
    Integer getRepairApprovalPendingCount();

    /**
     * 查询报修已审批数量
     */
    @Select("SELECT COUNT(*) FROM repair_approval WHERE repair_approval.hm_status = '1' and rp_status = '1'")
    Integer getRepairApprovalApprovedCount();

    /**
     * 查询报修已拒绝数量
     */
    @Select("SELECT COUNT(*) FROM repair_approval WHERE (repair_approval.hm_status = '1' and rp_status = '2') or hm_status = '2'")
    Integer getRepairApprovalRejectedCount();
}