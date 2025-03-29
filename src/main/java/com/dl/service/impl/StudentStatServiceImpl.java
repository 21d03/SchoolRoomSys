package com.dl.service.impl;

import com.dl.entity.vo.StudentGenderRatioVO;
import com.dl.mapper.StudentStatMapper;
import com.dl.service.StudentStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentStatServiceImpl implements StudentStatService {

    @Autowired
    private StudentStatMapper studentStatMapper;

    @Override
    public StudentGenderRatioVO getStudentGenderRatio() {
        StudentGenderRatioVO genderRatioVO = new StudentGenderRatioVO();
        
        // 获取男生人数
        Integer maleCount = studentStatMapper.getMaleStudentCount();
        genderRatioVO.setMaleCount(maleCount);
        
        // 获取女生人数
        Integer femaleCount = studentStatMapper.getFemaleStudentCount();
        genderRatioVO.setFemaleCount(femaleCount);
        
        return genderRatioVO;
    }
}
