package com.dl.service;

import com.dl.entity.dto.TeacherQueryDTO;
import com.dl.entity.vo.TeacherVO;

import java.util.List;

/**
 * 辅导员信息服务接口
 */
public interface TeacherService {
    
    /**
     * 查询辅导员列表
     * @param queryDTO 查询参数
     * @return 辅导员列表
     */
    List<TeacherVO> queryTeacherList(TeacherQueryDTO queryDTO);
} 