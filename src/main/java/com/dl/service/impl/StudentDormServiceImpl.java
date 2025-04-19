package com.dl.service.impl;

import com.dl.entity.vo.StudentDormVO;
import com.dl.mapper.StudentDormMapper;
import com.dl.service.StudentDormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudentDormServiceImpl implements StudentDormService {

    @Autowired
    private StudentDormMapper studentDormMapper;

    @Override
    public StudentDormVO getStudentDormInfo(String studentId) {
        log.info("查询学生宿舍信息, 学生ID: {}", studentId);
        
        // 获取学生宿舍基本信息
        StudentDormVO dormVO = studentDormMapper.getStudentDormInfo(studentId);
        if (dormVO == null) {
            log.warn("未找到学生宿舍信息, 学生ID: {}", studentId);
            return null;
        }
        
        // 获取宿管信息
        StudentDormVO houseMasterInfo = studentDormMapper.getHouseMasterInfo(dormVO.getBuildId());
        if (houseMasterInfo != null) {
            dormVO.setHmName(houseMasterInfo.getHmName());
            dormVO.setHmPhone(houseMasterInfo.getHmPhone());
        }
        
        // 获取室友信息
        dormVO.setRoommates(studentDormMapper.getRoommateInfo(
                dormVO.getBuildId(),
                dormVO.getRoomId(),
                studentId
        ));
        
        return dormVO;
    }
} 