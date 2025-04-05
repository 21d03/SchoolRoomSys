package com.dl.service.impl;

import com.dl.entity.vo.DormInfoVO;
import com.dl.entity.vo.DormStudentVO;
import com.dl.entity.vo.TeacherDormVO;
import com.dl.mapper.TeacherDormMapper;
import com.dl.service.TeacherDormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教师宿舍查询服务实现类
 */
@Service
@Slf4j
public class TeacherDormServiceImpl implements TeacherDormService {
    
    @Resource
    private TeacherDormMapper teacherDormMapper;
    
    @Override
    public List<TeacherDormVO> queryTeacherDorms(String teacherId) {
        log.info("查询教师[{}]管理的学生所住的宿舍", teacherId);
        return teacherDormMapper.queryTeacherDorms(teacherId);
    }
    
    @Override
    public DormInfoVO queryDormStudents(String buildId, String roomId) {
        log.info("查询宿舍[{}, {}]的学生信息", buildId, roomId);
        
        // 查询宿舍类型
        String roomType = teacherDormMapper.queryRoomType(buildId, roomId);
        
        // 查询宿舍学生
        List<DormStudentVO> students = teacherDormMapper.queryDormStudents(buildId, roomId);
        
        // 封装结果
        DormInfoVO dormInfoVO = new DormInfoVO();
        dormInfoVO.setRoomType(roomType);
        dormInfoVO.setStudents(students);
        
        return dormInfoVO;
    }
} 