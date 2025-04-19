package com.dl.service;

import com.dl.entity.vo.StudentDormVO;

public interface StudentDormService {
    
    /**
     * 获取学生宿舍信息
     * @param studentId 学生ID
     * @return 宿舍信息（包含宿管信息和室友信息）
     */
    StudentDormVO getStudentDormInfo(String studentId);
} 