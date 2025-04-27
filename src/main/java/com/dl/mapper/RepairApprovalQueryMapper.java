package com.dl.mapper;

import com.dl.entity.vo.RepairApprovalVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RepairApprovalQueryMapper {
    
    /**
     * 查询宿管用户的报修单列表
     * @param hmId 宿管ID
     * @return 报修单列表
     */
    @Select("SELECT " +
            "ra.id, ra.student_id, si.stu_name as student_name, ra.description as content, ra.create_time, " +
            "ra.hm_status, ra.rp_status, " +
            "CASE " +
            "   WHEN ra.hm_status = '0' OR ra.hm_status = '2' THEN ra.hm_id " +
            "   WHEN ra.hm_status = '1' THEN ra.rp_id " +
            "END as approver_id, " +
            "CASE " +
            "   WHEN ra.hm_status = '0' OR ra.hm_status = '2' THEN hm.hm_name " +
            "   WHEN ra.hm_status = '1' THEN rp.rp_name " +
            "END as approver_name, " +
            "CASE " +
            "   WHEN EXISTS (SELECT 1 FROM urge_record ur WHERE ur.approval_id = ra.id) THEN true " +
            "   ELSE false " +
            "END as has_urge " +
            "FROM repair_approval ra " +
            "LEFT JOIN student_info si ON ra.student_id = si.stu_id " +
            "LEFT JOIN house_master hm ON ra.hm_id = hm.hm_id " +
            "LEFT JOIN repair_people rp ON ra.rp_id = rp.rp_id " +
            "WHERE ra.hm_id = #{hmId} " +
            "ORDER BY " +
            "CASE WHEN ra.hm_status = '0' AND EXISTS (SELECT 1 FROM urge_record ur WHERE ur.approval_id = ra.id) THEN 0 " +
            "     WHEN ra.hm_status = '0' THEN 1 " +
            "     WHEN ra.hm_status = '1' AND ra.rp_status = '0' THEN 2 " +
            "     WHEN ra.hm_status = '1' AND ra.rp_status = '1' THEN 3 " +
            "     WHEN ra.hm_status = '1' AND ra.rp_status = '2' THEN 4 " +
            "     WHEN ra.hm_status = '2' THEN 5 " +
            "     ELSE 6 END, " +
            "ra.create_time DESC")
    List<RepairApprovalVO> getRepairApprovalList(@Param("hmId") String hmId);
} 