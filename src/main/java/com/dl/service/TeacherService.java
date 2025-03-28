package com.dl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.TeacherQueryDTO;
import com.dl.entity.vo.TeacherVO;

/**
 * 教师服务接口
 */
public interface TeacherService {

    /**
     * 分页查询教师列表
     * @param queryDTO 查询条件
     * @return 教师列表分页数据
     */
    IPage<TeacherVO> queryTeacherPage(TeacherQueryDTO queryDTO);
} 