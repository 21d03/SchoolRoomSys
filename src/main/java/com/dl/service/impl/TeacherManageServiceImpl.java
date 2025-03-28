package com.dl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.dto.TeacherManageQueryDTO;
import com.dl.entity.vo.TeacherManageVO;
import com.dl.mapper.TeacherManageMapper;
import com.dl.service.TeacherManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 教师管理服务实现类
 */
@Service
public class TeacherManageServiceImpl implements TeacherManageService {

    @Autowired
    private TeacherManageMapper teacherManageMapper;

    @Override
    public IPage<TeacherManageVO> queryTeacherManagePage(TeacherManageQueryDTO queryDTO) {
        Page<TeacherManageVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        return teacherManageMapper.queryTeacherManagePage(
                page,
                queryDTO.getTeacherId(),
                queryDTO.getName(),
                queryDTO.getSex(),
                queryDTO.getCollege()
        );
    }
} 