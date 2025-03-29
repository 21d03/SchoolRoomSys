package com.dl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PersonnelStatMapper {

    /**
     * 获取学生总数
     */
    @Select("SELECT COUNT(*) FROM student_info")
    Integer getStudentCount();

    /**
     * 获取教师总数
     */
    @Select("SELECT COUNT(*) FROM teacher_info")
    Integer getTeacherCount();

    /**
     * 获取宿管人数
     */
    @Select("SELECT COUNT(*) FROM house_master")
    Integer getDormManagerCount();

    /**
     * 获取维修人员数
     */
    @Select("SELECT COUNT(*) FROM repair_people")
    Integer getMaintenanceCount();
}
