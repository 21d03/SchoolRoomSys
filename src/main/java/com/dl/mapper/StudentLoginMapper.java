package com.dl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dl.entity.pojo.StudentUser;
import com.dl.entity.vo.StudentLoginVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentLoginMapper extends BaseMapper<StudentUser> {
    
    @Select("SELECT " +
            "su.user_id, su.user_name, su.name, su.phone, su.pass_word as password, " +
            "si.sex, si.college as college_name, si.profession, si.class_room, " +
            "si.teacher_id, ti.name as teacher_name, su2.phone as teacher_phone, " +
            "si.build_id, si.room_id " +
            "FROM student_user su " +
            "LEFT JOIN student_info si ON su.user_id = si.stu_id " +
            "LEFT JOIN teacher_info ti ON si.teacher_id = ti.teacher_id " +
            "LEFT JOIN school_user su2 ON si.teacher_id = su2.user_id " +
            "WHERE su.user_id = #{userId}")
    StudentLoginVO selectStudentWithDetails(String userId);
} 