package com.dl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SchoolIndexMapper {
    
    @Select("SELECT COUNT(*) FROM room_build")
    Integer getBuildingTotal();
    
    @Select("SELECT COUNT(*) FROM room_build WHERE is_used = '1'")
    Integer getBuildingNormal();
    
    @Select("SELECT COUNT(*) FROM room_build WHERE is_used = '0'")
    Integer getBuildingStopped();
    
    @Select("SELECT COUNT(*) FROM room_build_details")
    Integer getRoomTotal();
    
    @Select("SELECT COUNT(*) FROM room_build_details WHERE status = '1'")
    Integer getRoomNormal();
    
    @Select("SELECT COUNT(*) FROM room_build_details WHERE status = '0'")
    Integer getRoomStopped();
    
    @Select("SELECT COUNT(*) FROM repair_approval")
    Integer getRepairTotal();
    
    @Select("SELECT COUNT(*) FROM repair_approval WHERE (hm_status = '1' AND rp_status IN ('1', '2')) OR hm_status = '2'")
    Integer getRepairProcessed();
    
    @Select("SELECT COUNT(*) FROM repair_approval WHERE hm_status = '0' OR (hm_status = '1' AND rp_status = '0')")
    Integer getRepairPending();
    
    @Select("SELECT COUNT(*) FROM leave_approval")
    Integer getLeaveTotal();
    
    @Select("SELECT COUNT(*) FROM leave_approval WHERE status IN ('1', '2')")
    Integer getLeaveProcessed();
    
    @Select("SELECT COUNT(*) FROM leave_approval WHERE status = '0'")
    Integer getLeavePending();
} 