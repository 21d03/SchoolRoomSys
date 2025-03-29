package com.dl.service.impl;

import com.dl.entity.vo.CollegeVO;
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
}
