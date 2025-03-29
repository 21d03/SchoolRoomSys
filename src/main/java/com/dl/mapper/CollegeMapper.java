package com.dl.mapper;

import com.dl.entity.vo.CollegeVO;
import com.dl.entity.vo.CollegeStudentDistributionVO;
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

    /**
     * 获取学院学生分布
     */
    List<CollegeStudentDistributionVO> getCollegeStudentDistribution();

    /**
     * 获取指定学院的学生人数
     * @param collegeName 学院名称
     * @return 学生人数
     */
    @Select("SELECT COUNT(*) FROM student_info WHERE college = #{collegeName}")
    Integer getStudentCountByCollegeName(String collegeName);
}
