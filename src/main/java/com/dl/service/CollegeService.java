package com.dl.service;

import com.dl.entity.vo.CollegeVO;

import java.util.List;

public interface CollegeService {

    /**
     * 获取所有学院列表
     * @return 学院列表
     */
    List<CollegeVO> getAllColleges();
}
