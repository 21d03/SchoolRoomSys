package com.dl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.ClassInfo;
import com.dl.entity.SchoolUser;
import com.dl.entity.TeacherInfo;
import com.dl.entity.dto.TeacherAddDTO;
import com.dl.entity.dto.TeacherManageQueryDTO;
import com.dl.entity.dto.TeacherUpdateDTO;
import com.dl.entity.vo.TeacherManageVO;
import com.dl.mapper.*;
import com.dl.service.TeacherManageService;
import com.dl.utils.PasswordUtil;
import com.dl.utils.SpellUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 教师管理服务实现类
 */
@Service
public class TeacherManageServiceImpl implements TeacherManageService {

    @Autowired
    private TeacherManageMapper teacherManageMapper;

    @Autowired
    private SchoolUserMapper schoolUserMapper;

    @Autowired
    private TeacherInfoMapper teacherInfoMapper;

    @Autowired
    private SchoolUserTeacherMapper schoolUserTeacherMapper;

    @Autowired
    private TeacherInfoManageMapper teacherInfoManageMapper;

    @Autowired
    private ClassInfoMapper classInfoMapper;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addTeacher(TeacherAddDTO addDTO) {
        // 先检查teacher_id是否已存在
        TeacherInfo existTeacher = teacherInfoManageMapper.selectByIdtoAdd(addDTO.getTeacherId());
        if (existTeacher != null) {
            return false;
        }
        
        // 检查school_user表是否存在该用户
        SchoolUser existUser = schoolUserTeacherMapper.selectByIdToAdd(addDTO.getTeacherId());
        if (existUser != null) {
            return false;
        }

        String pinyin = SpellUtil.toPinyin(addDTO.getName());
        String level = String.valueOf(Integer.parseInt(addDTO.getTeacherId().substring(0, 2)));

        // 1. 新增用户表记录
        SchoolUser schoolUser = new SchoolUser();
        schoolUser.setUserId(addDTO.getTeacherId());
        schoolUser.setUserName(pinyin);
        schoolUser.setName(addDTO.getName());
        schoolUser.setPassWord(PasswordUtil.encrypt(PasswordUtil.DEFAULT_PASSWORD));
        schoolUser.setPhone(addDTO.getPhone());
        schoolUser.setLevel(level);
        schoolUser.setIsUsed(addDTO.getIsUsed());
        schoolUserTeacherMapper.insert(schoolUser);

        // 2. 新增教师信息表记录
        TeacherInfo teacherInfo = new TeacherInfo();
        teacherInfo.setTeacherId(addDTO.getTeacherId());
        teacherInfo.setName(addDTO.getName());
        teacherInfo.setTeacherName(pinyin);
        teacherInfo.setSex(addDTO.getSex());
        teacherInfo.setCollege(addDTO.getCollege());
        teacherInfoManageMapper.insert(teacherInfo);

        return true;
    }

    @Override
    public String getCollegeNameByLevel(String teacherId) {
        // 获取前两位并转为整数，自动去掉前导零
        String level = String.valueOf(Integer.parseInt(teacherId.substring(0, 2)));
        return teacherManageMapper.getCollegeNameByLevel(level);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateTeacher(TeacherUpdateDTO updateDTO) {
        // 检查教师是否存在
        SchoolUser existUser = schoolUserTeacherMapper.selectByIdToAdd(updateDTO.getTeacherId());
        if (existUser == null) {
            return false;
        }
        
        // 更新学校用户表信息
        SchoolUser schoolUser = new SchoolUser();
        schoolUser.setUserId(updateDTO.getTeacherId());
        schoolUser.setPhone(updateDTO.getPhone());
        schoolUser.setIsUsed(updateDTO.getIsUsed());


        int rows = schoolUserTeacherMapper.updateByIdTo(schoolUser.getUserId(),schoolUser.getPhone(),schoolUser.getIsUsed());
        return rows > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteTeacher(String teacherId) {
        // 1. 检查教师是否存在
        TeacherInfo teacherInfo = teacherInfoManageMapper.selectByIdtoAdd(teacherId);
        if (teacherInfo == null) {
            return 2; // 教师不存在
        }
        
        // 2. 检查教师是否有分管班级
        QueryWrapper<ClassInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id", teacherId);
        long count = classInfoMapper.selectCount(queryWrapper);
        if (count > 0) {
            return 1; // 有分管班级，不能删除
        }
        
        // 3. 删除教师信息
        teacherInfoMapper.deleteById(teacherId);
        
        // 4. 删除用户信息
        schoolUserMapper.deleteById(teacherId);
        
        return 0; // 删除成功
    }
} 