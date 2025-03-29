package com.dl.service;

import com.dl.entity.vo.ClassStudentDistributionVO;

import java.util.List;

public interface ClassStatService {

    /**
     * 获取班级人数分布数据
     * @param collegeId 学院ID，可为空
     * @return 班级人数分布数据
     */
    List<ClassStudentDistributionVO> getClassStudentDistribution(String collegeId);
}
