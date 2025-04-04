package com.dl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.dto.TeacherStudentQueryDTO;
import com.dl.entity.vo.StudentListVO;
import com.dl.mapper.StudentMapper;
import com.dl.result.PageResult;
import com.dl.service.TeacherStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教师管理学生服务实现类
 */
@Service
@Slf4j
public class TeacherStudentServiceImpl implements TeacherStudentService {
    
    @Resource
    private StudentMapper studentMapper;
    
    @Override
    public PageResult<StudentListVO> queryStudentList(TeacherStudentQueryDTO queryDTO) {
        log.info("教师[{}]查询学生列表，参数：{}", queryDTO.getTeacherId(), queryDTO);
        
        // 创建分页对象
        Page<StudentListVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        
        // 查询数据
        IPage<StudentListVO> pageResult = studentMapper.queryTeacherStudentPage(
                page,
                queryDTO.getTeacherId(),
                queryDTO.getStudentId(),
                queryDTO.getName(),
                queryDTO.getProfession(),
                queryDTO.getClassName()
        );
        
        // 封装结果
        return new PageResult<>(pageResult.getTotal(), pageResult.getRecords());
    }
    
} 