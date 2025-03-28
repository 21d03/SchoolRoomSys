package com.dl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.dto.TeacherQueryDTO;
import com.dl.entity.vo.TeacherVO;
import com.dl.mapper.TeacherMapper;
import com.dl.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 教师服务实现类
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public IPage<TeacherVO> queryTeacherPage(TeacherQueryDTO queryDTO) {
        Page<TeacherVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        return teacherMapper.queryTeacherPage(
                page,
                queryDTO.getTeacherId(),
                queryDTO.getTeacherName(),
                queryDTO.getCollege(),
                queryDTO.getProfession(),
                queryDTO.getClassName()
        );
    }
} 