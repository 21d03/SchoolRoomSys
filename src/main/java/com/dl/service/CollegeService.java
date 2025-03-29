package com.dl.service;

import com.dl.entity.vo.CollegeVO;
import com.dl.entity.vo.CollegeStudentDistributionVO;

import java.util.List;

public interface CollegeService {

    /**
     * 获取所有学院列表
     * @return 学院列表
     */
    List<CollegeVO> getAllColleges();

    /**
     * 获取各学院学生分布
     * @return 各学院学生分布数据
     */
    List<CollegeStudentDistributionVO> getCollegeStudentDistribution();
}
