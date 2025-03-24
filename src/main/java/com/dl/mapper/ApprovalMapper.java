package com.dl.mapper;

import com.dl.entity.vo.RecentApprovalVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ApprovalMapper {
    
    @Select("SELECT " +
            "ra.id as approval_id, " +
            "'2' as approval_type, " +
            "ra.student_id as student_id, " +
            "si.stu_name as student_name, " +
            "CONCAT(ra.item_name, '：', ra.description) as content, " +
            "CASE " +
            "  WHEN ra.hm_status = '2' THEN '2' " +
            "  WHEN ra.hm_status = '1' AND ra.rp_status = '2' THEN '2' " +
            "  WHEN ra.hm_status = '1' AND ra.rp_status = '1' THEN '1' " +
            "  ELSE '0' " +
            "END as status, " +
            "ra.create_time " +
            "FROM repair_approval ra " +
            "LEFT JOIN student_info si ON ra.student_id = si.stu_id " +
            "UNION ALL " +
            "SELECT " +
            "la.id as approval_id, " +
            "'1' as approval_type, " +
            "la.student_id as student_id, " +
            "si.stu_name as student_name, " +
            "la.reason as content, " +
            "la.status, " +
            "la.create_time " +
            "FROM leave_approval la " +
            "LEFT JOIN student_info si ON la.student_id = si.stu_id " +
            "ORDER BY create_time DESC " +
            "LIMIT 10")
    List<RecentApprovalVO> getRecentApprovals();
} 