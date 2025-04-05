package com.dl.service;

import com.dl.entity.vo.DormInfoVO;
import com.dl.entity.vo.TeacherDormVO;

import java.util.List;

/**
 * 教师宿舍查询服务接口
 */
public interface TeacherDormService {
    
    /**
     * 查询教师管理的学生所住的宿舍
     * @param teacherId 教师ID
     * @return 宿舍列表
     */
    List<TeacherDormVO> queryTeacherDorms(String teacherId);
    
    /**
     * 查询宿舍学生信息
     * @param buildId 楼栋ID
     * @param roomId 房间号
     * @return 宿舍详细信息
     */
    DormInfoVO queryDormStudents(String buildId, String roomId);
} 