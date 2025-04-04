package com.dl.service;

import com.dl.entity.dto.TeacherProfessionQueryDTO;
import com.dl.entity.vo.ClassNameVO;
import com.dl.entity.vo.ProfessionVO;

import java.util.List;

/**
 * 教师专业班级查询服务接口
 */
public interface TeacherProfessionService {
    
    /**
     * 查询教师管理的专业和班级
     * @param queryDTO 查询参数
     * @return 如果只传教师ID，返回专业列表；如果同时传专业名称，返回班级列表
     */
    Object queryProfessionAndClass(TeacherProfessionQueryDTO queryDTO);
    
    /**
     * 查询教师管理的所有专业
     * @param teacherId 教师ID
     * @return 专业列表
     */
    List<ProfessionVO> queryTeacherProfessions(String teacherId);
    
    /**
     * 查询教师管理的指定专业下的所有班级
     * @param teacherId 教师ID
     * @param profession 专业名称
     * @return 班级列表
     */
    List<ClassNameVO> queryTeacherClassesByProfession(String teacherId, String profession);
} 