package com.dl.mapper;

import com.dl.entity.vo.ClassNameVO;
import com.dl.entity.vo.ProfessionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 教师专业班级查询Mapper接口
 */
@Mapper
public interface TeacherProfessionMapper {
    
    /**
     * 查询教师管理的所有专业
     * @param teacherId 教师ID
     * @return 专业列表
     */
    @Select("SELECT DISTINCT profession FROM class_info WHERE teacher_id = #{teacherId}")
    List<ProfessionVO> queryTeacherProfessions(@Param("teacherId") String teacherId);
    
    /**
     * 查询教师管理的指定专业下的所有班级
     * @param teacherId 教师ID
     * @param profession 专业名称
     * @return 班级列表
     */
    @Select("SELECT class_name as className FROM class_info WHERE teacher_id = #{teacherId} AND profession = #{profession}")
    List<ClassNameVO> queryTeacherClassesByProfession(@Param("teacherId") String teacherId, @Param("profession") String profession);
} 