package com.dl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.pojo.StudentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author dongliang
 * @date 2024/09/23 21:51:51
 * @description
 **/
@Mapper
public interface SchoolManageStuMapper extends BaseMapper<StudentInfo> {
    @Select("SELECT * FROM student_info order by stu_id")
    Page<StudentInfo> pageQuery(Page<StudentInfo> page);
}
