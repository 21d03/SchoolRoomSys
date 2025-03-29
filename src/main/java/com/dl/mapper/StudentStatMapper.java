package com.dl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentStatMapper {

    /**
     * 获取男生人数
     */
    @Select("SELECT COUNT(*) FROM student_info WHERE sex = '男'")
    Integer getMaleStudentCount();

    /**
     * 获取女生人数
     */
    @Select("SELECT COUNT(*) FROM student_info WHERE sex = '女'")
    Integer getFemaleStudentCount();
}
