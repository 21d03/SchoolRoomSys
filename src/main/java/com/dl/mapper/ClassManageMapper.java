package com.dl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.vo.ClassVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClassManageMapper {

    /**
     * 分页查询班级列表
     */
    IPage<ClassVO> queryClassPage(Page<ClassVO> page,
                                 @Param("collegeName") String collegeName,
                                 @Param("className") String className);

    /**
     * 根据学院名称查询学院ID
     */
    @Select("SELECT college_id FROM college_info WHERE college_name = #{collegeName}")
    String getCollegeIdByName(String collegeName);

    /**
     * 新增班级
     */
    void addClass(@Param("collegeId") String collegeId,
                 @Param("collegeName") String collegeName,
                 @Param("profession") String profession,
                 @Param("className") String className);

    /**
     * 更新班级信息
     */
    void updateClass(@Param("collegeName") String collegeName,
                    @Param("oldProfession") String oldProfession,
                    @Param("newProfession") String newProfession,
                    @Param("oldClassName") String oldClassName,
                    @Param("newClassName") String newClassName);

    /**
     * 更新学生信息
     */
    void updateStudentInfo(@Param("oldProfession") String oldProfession,
                          @Param("newProfession") String newProfession,
                          @Param("oldClassName") String oldClassName,
                          @Param("newClassName") String newClassName);

    /**
     * 检查班级是否有教师
     */
    @Select("SELECT COUNT(*) FROM class_info WHERE profession = #{profession} AND class_name = #{className} AND teacher_id IS NOT NULL")
    Integer checkClassHasTeacher(@Param("profession") String profession,
                               @Param("className") String className);

    /**
     * 检查班级是否有学生
     */
    @Select("SELECT COUNT(*) FROM student_info WHERE profession = #{profession} AND class_room = #{className}")
    Integer checkClassHasStudent(@Param("profession") String profession,
                               @Param("className") String className);

    /**
     * 删除班级
     */
    @Select("DELETE FROM class_info WHERE profession = #{profession} AND class_name = #{className}")
    void deleteClass(@Param("profession") String profession,
                    @Param("className") String className);

    /**
     * 获取班级学生人数
     */
    @Select("SELECT COUNT(*) FROM student_info WHERE profession = #{profession} AND class_room = #{className}")
    Integer getStudentCount(@Param("profession") String profession, @Param("className") String className);

    /**
     * 检查班级是否已存在
     */
    @Select("SELECT COUNT(*) FROM class_info WHERE profession = #{profession} AND class_name = #{className}")
    Integer checkClassExists(@Param("profession") String profession, @Param("className") String className);
}
