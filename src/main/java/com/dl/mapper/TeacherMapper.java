package com.dl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.TeacherInfo;
import com.dl.entity.vo.TeacherVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 辅导员信息Mapper接口
 */
@Mapper
public interface TeacherMapper extends BaseMapper<TeacherInfo> {
    
    /**
     * 分页查询教师列表
     * @param page 分页参数
     * @param teacherId 教师ID
     * @param teacherName 教师姓名
     * @param college 学院
     * @param profession 专业
     * @param className 班级
     * @return 教师列表
     */
    IPage<TeacherVO> queryTeacherPage(Page<TeacherVO> page,
                                      @Param("teacherId") String teacherId,
                                      @Param("teacherName") String teacherName,
                                      @Param("college") String college,
                                      @Param("profession") String profession,
                                      @Param("className") String className);
} 