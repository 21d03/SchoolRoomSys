package com.dl.mapper;

import com.dl.entity.vo.CollegeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollegeMapper {

    /**
     * 获取所有学院列表
     */
    @Select("SELECT college_id as collegeId, college_name as collegeName FROM college_info")
    List<CollegeVO> getAllColleges();
}
