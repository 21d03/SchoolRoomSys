package com.dl.mapper;

import com.dl.entity.vo.RepairDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 报修详情查询Mapper
 */
@Mapper
public interface RepairDetailMapper {
    
    /**
     * 根据报修单ID查询报修详情
     * @param repairId 报修单ID
     * @return 报修详情
     */
    @Select("SELECT " +
            "ra.id, ra.student_id as studentId, si.stu_name as studentName, " +
            "ra.repair_type as repairType, ra.room_id as roomId, ra.public_area as publicArea, " +
            "ra.item_name as itemName, ra.description, ra.images, " +
            "ra.hm_id as hmId, hm.hm_name as hmName, ra.hm_status as hmStatus, " +
            "ra.hm_opinion as hmOpinion, date_format(ra.hm_time, '%Y-%m-%d %H:%i:%s') as hmTime, " +
            "ra.rp_id as rpId, rp.rp_name as rpName, ra.rp_status as rpStatus, " +
            "ra.rp_opinion as rpOpinion, date_format(ra.rp_time, '%Y-%m-%d %H:%i:%s') as rpTime, " +
            "date_format(ra.create_time, '%Y-%m-%d %H:%i:%s') as createTime, " +
            "date_format(ra.update_time, '%Y-%m-%d %H:%i:%s') as updateTime " +
            "FROM repair_approval ra " +
            "LEFT JOIN student_info si ON ra.student_id = si.stu_id " +
            "LEFT JOIN house_master hm ON ra.hm_id = hm.hm_id " +
            "LEFT JOIN repair_people rp ON ra.rp_id = rp.rp_id " +
            "WHERE ra.id = #{repairId}")
    RepairDetailVO getRepairDetail(@Param("repairId") String repairId);
} 