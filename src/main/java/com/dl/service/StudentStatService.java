package com.dl.service;

import com.dl.entity.vo.StudentGenderRatioVO;

public interface StudentStatService {

    /**
     * 获取学生性别比例
     * @return 学生性别比例数据
     */
    StudentGenderRatioVO getStudentGenderRatio();
}
