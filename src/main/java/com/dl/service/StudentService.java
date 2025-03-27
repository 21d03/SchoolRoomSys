package com.dl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.StudentQueryDTO;
import com.dl.entity.vo.StudentVO;

public interface StudentService {
    
    /**
     * 分页查询学生列表
     * @param queryDTO 查询参数
     * @return 分页结果
     */
    IPage<StudentVO> queryStudentPage(StudentQueryDTO queryDTO);
    
} 