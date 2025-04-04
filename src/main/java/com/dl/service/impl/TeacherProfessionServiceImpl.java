package com.dl.service.impl;

import com.dl.entity.dto.TeacherProfessionQueryDTO;
import com.dl.entity.vo.ClassNameVO;
import com.dl.entity.vo.ProfessionVO;
import com.dl.mapper.TeacherProfessionMapper;
import com.dl.service.TeacherProfessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教师专业班级查询服务实现类
 */
@Service
@Slf4j
public class TeacherProfessionServiceImpl implements TeacherProfessionService {
    
    @Resource
    private TeacherProfessionMapper teacherProfessionMapper;
    
    @Override
    public Object queryProfessionAndClass(TeacherProfessionQueryDTO queryDTO) {
        String teacherId = queryDTO.getTeacherId();
        String profession = queryDTO.getProfession();
        
        // 如果专业名称为空，查询教师管理的所有专业
        if (!StringUtils.hasText(profession)) {
            log.info("查询教师[{}]管理的所有专业", teacherId);
            return queryTeacherProfessions(teacherId);
        }
        
        // 如果专业名称不为空，查询教师管理的指定专业下的所有班级
        log.info("查询教师[{}]管理的专业[{}]下的所有班级", teacherId, profession);
        return queryTeacherClassesByProfession(teacherId, profession);
    }
    
    @Override
    public List<ProfessionVO> queryTeacherProfessions(String teacherId) {
        return teacherProfessionMapper.queryTeacherProfessions(teacherId);
    }
    
    @Override
    public List<ClassNameVO> queryTeacherClassesByProfession(String teacherId, String profession) {
        return teacherProfessionMapper.queryTeacherClassesByProfession(teacherId, profession);
    }
} 