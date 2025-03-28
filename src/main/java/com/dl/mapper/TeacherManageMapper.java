package com.dl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.vo.TeacherManageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TeacherManageMapper {

    /**
     * 分页查询教师管理信息
     * @param page 分页参数
     * @param teacherId 教师ID
     * @param name 教师姓名
     * @param sex 性别
     * @param college 所属学院
     * @return 教师管理信息列表
     */
    IPage<TeacherManageVO> queryTeacherManagePage(Page<TeacherManageVO> page,
                                                 @Param("teacherId") String teacherId,
                                                 @Param("name") String name,
                                                 @Param("sex") String sex,
                                                 @Param("college") String college);

    String getCollegeNameByLevel(String level);
} 