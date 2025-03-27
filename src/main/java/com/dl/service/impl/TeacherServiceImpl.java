package com.dl.service.impl;

import com.dl.entity.dto.TeacherQueryDTO;
import com.dl.entity.vo.TeacherVO;
import com.dl.mapper.TeacherMapper;
import com.dl.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 辅导员信息服务实现类
 */
@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;
    
    @Override
    public List<TeacherVO> queryTeacherList(TeacherQueryDTO queryDTO) {
        log.info("查询辅导员列表: {}", queryDTO);
        return teacherMapper.queryTeacherList(
                queryDTO.getTeacherName(),
                queryDTO.getCollege()
        );
    }
} 