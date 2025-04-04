package com.dl.service;

import com.dl.entity.dto.TeacherStudentQueryDTO;
import com.dl.entity.vo.StudentListVO;
import com.dl.result.PageResult;

/**
 * 教师管理学生服务接口
 */
public interface TeacherStudentService {
    
    /**
     * 教师查询学生列表
     * @param queryDTO 查询参数
     * @return 学生列表分页结果
     */
    PageResult<StudentListVO> queryStudentList(TeacherStudentQueryDTO queryDTO);
    
} 