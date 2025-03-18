package com.dl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dl.entity.pojo.SchoolUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author dongliang
 * @date 2024/09/22 21:22:22
 * @description
 **/
@Mapper
public interface SchoolLoginMapper extends BaseMapper<SchoolUser> {
    
    @Select("SELECT su.*, ti.sex as teacher_sex, ci.college_name " +
            "FROM school_user su " +
            "LEFT JOIN teacher_info ti ON su.user_id = ti.teacher_id " +
            "LEFT JOIN college_info ci ON su.level = ci.college_id " +
            "WHERE su.user_id = #{userId}")
    SchoolUser selectUserWithDetails(String userId);
}
