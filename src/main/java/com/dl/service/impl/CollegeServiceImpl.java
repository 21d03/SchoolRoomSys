package com.dl.service.impl;

import com.dl.entity.vo.CollegeVO;
import com.dl.entity.vo.CollegeStudentDistributionVO;
import com.dl.mapper.CollegeMapper;
import com.dl.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public List<CollegeVO> getAllColleges() {
        return collegeMapper.getAllColleges();
    }

    @Override
    public List<CollegeStudentDistributionVO> getCollegeStudentDistribution() {
        // 获取学院基本信息
        List<CollegeStudentDistributionVO> distributionList = collegeMapper.getCollegeStudentDistribution();
        
        // 填充每个学院的学生人数
        for (CollegeStudentDistributionVO college : distributionList) {
            Integer studentCount = collegeMapper.getStudentCountByCollegeName(college.getCollegeName());
            college.setStudentCount(studentCount);
        }
        
        return distributionList;
    }
}
