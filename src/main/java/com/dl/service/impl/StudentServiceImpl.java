package com.dl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.dto.StudentQueryDTO;
import com.dl.entity.vo.StudentVO;
import com.dl.mapper.StudentMapper;
import com.dl.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生信息 服务实现类
 * </p>
 *
 * @author dongliang
 * @since 2024-11-01
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public IPage<StudentVO> queryStudentPage(StudentQueryDTO queryDTO) {
        Page<StudentVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        return studentMapper.queryStudentPage(page, 
            queryDTO.getStuId(), 
            queryDTO.getStuName(), 
            queryDTO.getCollege(), 
            queryDTO.getSex());
    }
} 