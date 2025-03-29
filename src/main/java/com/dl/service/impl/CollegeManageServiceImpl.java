package com.dl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.dto.CollegeQueryDTO;
import com.dl.entity.vo.CollegeVO;
import com.dl.mapper.CollegeManageMapper;
import com.dl.service.CollegeManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CollegeManageServiceImpl implements CollegeManageService {

    @Autowired
    private CollegeManageMapper collegeManageMapper;

    @Override
    public IPage<CollegeVO> queryCollegePage(CollegeQueryDTO queryDTO) {
        Page<CollegeVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        return collegeManageMapper.queryCollegePage(page, queryDTO.getCollegeId(), queryDTO.getCollegeName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addCollege(String collegeId, String collegeName) {
        // 检查ID是否存在
        if (collegeManageMapper.checkCollegeIdExists(collegeId) > 0) {
            return false;
        }
        
        // 检查名称是否存在
        if (collegeManageMapper.checkCollegeNameExists(collegeName) > 0) {
            return false;
        }
        
        // 新增学院
        collegeManageMapper.insertCollege(collegeId, collegeName);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteCollege(String collegeId, String collegeName) {
        // 检查是否有学生
        if (collegeManageMapper.checkCollegeHasStudents(collegeName) > 0) {
            return 1;
        }
        
        // 检查是否有教师
        if (collegeManageMapper.checkCollegeHasTeachers(collegeName) > 0) {
            return 2;
        }
        
        // 删除学院
        collegeManageMapper.deleteCollege(collegeId, collegeName);
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateCollege(String collegeId, String oldCollegeName, String newCollegeName) {
        // 检查新名称是否已存在（排除自己）
        if (collegeManageMapper.checkCollegeNameExists(newCollegeName) > 0 
                && !oldCollegeName.equals(newCollegeName)) {
            return false;
        }
        
        // 更新college_info表
        collegeManageMapper.updateCollegeName(collegeId, oldCollegeName, newCollegeName);
        
        // 更新class_info表
        collegeManageMapper.updateClassInfoCollegeName(collegeId, newCollegeName);
        
        // 更新student_info表
        collegeManageMapper.updateStudentInfoCollegeName(oldCollegeName, newCollegeName);
        
        // 更新teacher_info表
        collegeManageMapper.updateTeacherInfoCollegeName(oldCollegeName, newCollegeName);
        
        return true;
    }
}
