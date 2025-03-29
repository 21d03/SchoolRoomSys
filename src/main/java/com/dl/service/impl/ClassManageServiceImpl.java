package com.dl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.dto.ClassAddDTO;
import com.dl.entity.dto.ClassQueryDTO;
import com.dl.entity.dto.ClassUpdateDTO;
import com.dl.entity.vo.ClassVO;
import com.dl.mapper.ClassManageMapper;
import com.dl.service.ClassManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClassManageServiceImpl implements ClassManageService {

    @Autowired
    private ClassManageMapper classManageMapper;

    @Override
    public IPage<ClassVO> queryClassPage(ClassQueryDTO queryDTO) {
        Page<ClassVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        return classManageMapper.queryClassPage(page, queryDTO.getCollegeName(), queryDTO.getClassName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addClass(ClassAddDTO addDTO) {
        // 根据学院名称获取学院ID
        String collegeId = classManageMapper.getCollegeIdByName(addDTO.getCollegeName());
        if (collegeId == null) {
            return false;
        }
        
        // 检查班级是否已存在
        Integer count = classManageMapper.checkClassExists(addDTO.getProfession(), addDTO.getClassName());
        if (count > 0) {
            return false;
        }
        
        // 新增班级
        classManageMapper.addClass(collegeId, addDTO.getCollegeName(), 
                                 addDTO.getProfession(), addDTO.getClassName());
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateClass(ClassUpdateDTO updateDTO) {
        // 检查新班级是否已存在（排除自己）
        if (!updateDTO.getOldProfession().equals(updateDTO.getNewProfession()) 
                || !updateDTO.getOldClassName().equals(updateDTO.getNewClassName())) {
            Integer count = classManageMapper.checkClassExists(
                updateDTO.getNewProfession(), 
                updateDTO.getNewClassName()
            );
            if (count > 0) {
                return false;
            }
        }
        
        // 更新班级信息
        classManageMapper.updateClass(updateDTO.getCollegeName(),
                                    updateDTO.getOldProfession(),
                                    updateDTO.getNewProfession(),
                                    updateDTO.getOldClassName(),
                                    updateDTO.getNewClassName());
        
        // 更新学生信息
        classManageMapper.updateStudentInfo(updateDTO.getOldProfession(),
                                          updateDTO.getNewProfession(),
                                          updateDTO.getOldClassName(),
                                          updateDTO.getNewClassName());
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteClass(String profession, String className) {
        // 检查是否有教师
        if (classManageMapper.checkClassHasTeacher(profession, className) > 0) {
            return 1;
        }
        
        // 检查是否有学生
        if (classManageMapper.checkClassHasStudent(profession, className) > 0) {
            return 2;
        }
        
        // 删除班级
        classManageMapper.deleteClass(profession, className);
        return 0;
    }
}
