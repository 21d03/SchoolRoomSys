package com.dl.service;

import com.dl.entity.vo.StudentIndexVO;

public interface StudentIndexService {
    
    /**
     * 获取学生首页数据统计
     * @param studentId 学生ID
     * @return 首页数据统计
     */
    StudentIndexVO getStudentIndexData(String studentId);
} 