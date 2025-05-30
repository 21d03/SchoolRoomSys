package com.dl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.vo.CollegeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CollegeManageMapper {

    /**
     * 分页查询学院列表
     */
    IPage<CollegeVO> queryCollegePage(Page<CollegeVO> page,
                                     @Param("collegeId") String collegeId,
                                     @Param("collegeName") String collegeName);

    /**
     * 根据学院ID查询
     */
    @Select("SELECT COUNT(*) FROM college_info WHERE college_id = #{collegeId}")
    Integer checkCollegeIdExists(String collegeId);

    /**
     * 根据学院名称查询
     */
    @Select("SELECT COUNT(*) FROM college_info WHERE college_name = #{collegeName}")
    Integer checkCollegeNameExists(String collegeName);

    /**
     * 新增学院
     */
    @Select("INSERT INTO college_info(college_id, college_name) VALUES(#{collegeId}, #{collegeName})")
    void insertCollege(@Param("collegeId") String collegeId, @Param("collegeName") String collegeName);

    /**
     * 检查学院是否有学生
     */
    @Select("SELECT COUNT(*) FROM student_info WHERE college = #{collegeName}")
    Integer checkCollegeHasStudents(String collegeName);

    /**
     * 检查学院是否有教师
     */
    @Select("SELECT COUNT(*) FROM teacher_info WHERE college = #{collegeName}")
    Integer checkCollegeHasTeachers(String collegeName);

    /**
     * 删除学院
     */
    @Select("DELETE FROM college_info WHERE college_id = #{collegeId} AND college_name = #{collegeName}")
    void deleteCollege(@Param("collegeId") String collegeId, @Param("collegeName") String collegeName);

    /**
     * 更新学院名称
     */
    @Update("UPDATE college_info SET college_name = #{newCollegeName} WHERE college_id = #{collegeId} AND college_name = #{oldCollegeName}")
    void updateCollegeName(@Param("collegeId") String collegeId, 
                          @Param("oldCollegeName") String oldCollegeName,
                          @Param("newCollegeName") String newCollegeName);

    /**
     * 更新班级表中的学院名称
     */
    @Update("UPDATE class_info SET college_name = #{newCollegeName} WHERE college_id = #{collegeId}")
    void updateClassInfoCollegeName(@Param("collegeId") String collegeId, 
                                   @Param("newCollegeName") String newCollegeName);

    /**
     * 更新学生表中的学院名称
     */
    @Update("UPDATE student_info SET college = #{newCollegeName} WHERE college = #{oldCollegeName}")
    void updateStudentInfoCollegeName(@Param("oldCollegeName") String oldCollegeName, 
                                     @Param("newCollegeName") String newCollegeName);

    /**
     * 更新教师表中的学院名称
     */
    @Update("UPDATE teacher_info SET college = #{newCollegeName} WHERE college = #{oldCollegeName}")
    void updateTeacherInfoCollegeName(@Param("oldCollegeName") String oldCollegeName, 
                                     @Param("newCollegeName") String newCollegeName);
}
