package com.dl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.pojo.StudentInfo;
import com.dl.entity.vo.StudentListVO;
import com.dl.entity.vo.StudentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 学生信息 Mapper 接口
 * </p>
 *
 * @author dongliang
 * @since 2024-11-01
 */
@Mapper
public interface StudentMapper extends BaseMapper<StudentInfo> {

    /**
     * 分页查询学生列表
     * @param page 分页参数
     * @param stuId 学号
     * @param stuName 学生姓名
     * @param college 所属学院
     * @param sex 性别
     * @return 分页结果
     */
    IPage<StudentVO> queryStudentPage(Page<StudentVO> page, 
                                    @Param("stuId") String stuId,
                                    @Param("stuName") String stuName,
                                    @Param("college") String college,
                                    @Param("sex") String sex);
    
    /**
     * 教师查询所管理的学生列表
     * @param page 分页参数
     * @param teacherId 教师ID
     * @param studentId 学号
     * @param name 学生姓名
     * @param profession 专业
     * @param className 班级名称
     * @return 分页结果
     */
    IPage<StudentListVO> queryTeacherStudentPage(Page<StudentListVO> page,
                                               @Param("teacherId") String teacherId,
                                               @Param("studentId") String studentId,
                                               @Param("name") String name,
                                               @Param("profession") String profession,
                                               @Param("className") String className);
} 