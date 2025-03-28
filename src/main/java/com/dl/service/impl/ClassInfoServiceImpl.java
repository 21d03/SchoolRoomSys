package com.dl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.dto.AssignClassDTO;
import com.dl.entity.dto.ClassInfoQueryDTO;
import com.dl.entity.dto.UnassignClassDTO;
import com.dl.entity.vo.ClassInfoVO;
import com.dl.entity.vo.UnassignedClassVO;
import com.dl.mapper.ClassInfoMapper;
import com.dl.service.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 班级信息服务实现类
 */
@Service
public class ClassInfoServiceImpl implements ClassInfoService {

    @Autowired
    private ClassInfoMapper classInfoMapper;

    @Override
    public IPage<ClassInfoVO> queryClassInfoPage(ClassInfoQueryDTO queryDTO) {
        Page<ClassInfoVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        return classInfoMapper.queryClassInfoPage(
                page,
                queryDTO.getCollegeName(),
                queryDTO.getProfession(),
                queryDTO.getClassName()
        );
    }

    @Override
    public List<UnassignedClassVO> queryUnassignedClasses(String collegeName) {
        return classInfoMapper.queryUnassignedClasses(collegeName);
    }

    @Override
    public boolean assignClass(AssignClassDTO assignClassDTO) {
        int rows = classInfoMapper.assignClass(
                assignClassDTO.getCollegeName(),
                assignClassDTO.getProfession(),
                assignClassDTO.getClassName(),
                assignClassDTO.getTeacherId(),
                assignClassDTO.getTeacherName()
        );
        return rows > 0;
    }

    @Override
public boolean unassignClass(UnassignClassDTO unassignClassDTO) {
    int rows = classInfoMapper.unassignClass(
            unassignClassDTO.getCollegeName(),
            unassignClassDTO.getProfession(),
            unassignClassDTO.getClassName()
    );
    return rows > 0;
}
} 