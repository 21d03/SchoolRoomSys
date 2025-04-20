package com.dl.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UrgeRecordMapper {
    
    /**
     * 查询是否已经催促过
     * @param approvalId 审批单ID
     * @param fromId 催促人ID
     * @param toId 接收人ID
     * @param urgeType 催促类型
     * @return 记录数
     */
    @Select("SELECT COUNT(*) FROM urge_record WHERE approval_id = #{approvalId} " +
            "AND from_id = #{fromId} AND to_id = #{toId} AND urge_type = #{urgeType}")
    int checkUrgeExists(@Param("approvalId") String approvalId,
                        @Param("fromId") String fromId,
                        @Param("toId") String toId,
                        @Param("urgeType") String urgeType);
    
    /**
     * 新增催促记录
     * @param id 催促记录ID
     * @param approvalId 审批单ID
     * @param approvalType 审批类型
     * @param urgeType 催促类型
     * @param urgeContent 催促内容
     * @param fromId 催促人ID
     * @param toId 接收人ID
     * @return 影响行数
     */
    @Insert("INSERT INTO urge_record(id, approval_id, approval_type, urge_type, urge_content, from_id, to_id, create_time) " +
            "VALUES(#{id}, #{approvalId}, #{approvalType}, #{urgeType}, #{urgeContent}, #{fromId}, #{toId}, NOW())")
    int insertUrgeRecord(@Param("id") String id,
                         @Param("approvalId") String approvalId,
                         @Param("approvalType") String approvalType,
                         @Param("urgeType") String urgeType,
                         @Param("urgeContent") String urgeContent,
                         @Param("fromId") String fromId,
                         @Param("toId") String toId);
} 