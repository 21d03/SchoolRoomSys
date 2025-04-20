package com.dl.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RepairApplicationMapper {

    /**
     * 查询当天最大的报修申请ID
     * @param prefix 前缀，格式RPyyyyMMdd
     * @return 当天最大的报修申请ID，如果没有则返回null
     */
    @Select("SELECT MAX(id) FROM repair_approval WHERE id LIKE CONCAT(#{prefix}, '%')")
    String getMaxRepairIdByDate(@Param("prefix") String prefix);
    
    /**
     * 查询学生所属宿舍楼ID
     * @param studentId 学生ID
     * @return 宿舍楼ID
     */
    @Select("SELECT build_id FROM student_info WHERE stu_id = #{studentId}")
    String getBuildIdByStudentId(@Param("studentId") String studentId);
    
    /**
     * 查询宿舍楼管理员ID
     * @param buildId 宿舍楼ID
     * @return 宿管ID
     */
    @Select("SELECT hm_id FROM house_master WHERE build_id = #{buildId}")
    String getHmIdByBuildId(@Param("buildId") String buildId);
    
    /**
     * 插入报修申请记录
     */
    @Insert("INSERT INTO repair_approval(id, student_id, repair_type, room_id, public_area, " +
            "item_name, description, images, hm_id, create_time) " +
            "VALUES(#{id}, #{studentId}, #{repairType}, #{roomId}, #{publicArea}, " +
            "#{itemName}, #{description}, #{images}, #{hmId}, #{createTime})")
    int insertRepairApplication(@Param("id") String id,
                              @Param("studentId") String studentId,
                              @Param("repairType") String repairType,
                              @Param("roomId") String roomId,
                              @Param("publicArea") String publicArea,
                              @Param("itemName") String itemName,
                              @Param("description") String description,
                              @Param("images") String images,
                              @Param("hmId") String hmId,
                              @Param("createTime") String createTime);
} 