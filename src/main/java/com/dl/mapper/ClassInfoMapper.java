package com.dl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.ClassInfo;
import com.dl.entity.vo.ClassInfoVO;
import com.dl.entity.vo.UnassignedClassVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ClassInfoMapper extends BaseMapper<ClassInfo> {

    /**
     * 分页查询班级信息
     * @param page 分页参数
     * @param collegeName 学院名称
     * @param profession 专业名称
     * @param className 班级名称
     * @return 班级信息列表
     */
    IPage<ClassInfoVO> queryClassInfoPage(Page<ClassInfoVO> page, 
                                         @Param("collegeName") String collegeName,
                                         @Param("profession") String profession,
                                         @Param("className") String className);

    /**
     * 查询未分配辅导员的班级
     * @param collegeName 学院名称
     * @return 未分配辅导员的班级列表
     */
    List<UnassignedClassVO> queryUnassignedClasses(@Param("collegeName") String collegeName);

    /**
     * 分配班级辅导员
     * @param collegeName 学院名称
     * @param profession 专业名称
     * @param className 班级名称
     * @param teacherId 教师ID
     * @param teacherName 教师姓名
     * @return 更新行数
     */
    int assignClass(@Param("collegeName") String collegeName,
                   @Param("profession") String profession,
                   @Param("className") String className,
                   @Param("teacherId") String teacherId,
                   @Param("teacherName") String teacherName);

    /**
     * 取消班级分管
     * @param collegeName 学院名称
     * @param profession 专业名称
     * @param className 班级名称
     * @return 更新行数
     */
    int unassignClass(@Param("collegeName") String collegeName,
                     @Param("profession") String profession,
                     @Param("className") String className);

    @Update("update student_info set teacher_id = #{teacherId},teacher_name = #{teacherName} " +
            "where college = #{collegeName} and profession = #{profession} and class_room = #{className}")
    void updateStudentInfo(String teacherName, String teacherId, String collegeName, String profession, String className);
}