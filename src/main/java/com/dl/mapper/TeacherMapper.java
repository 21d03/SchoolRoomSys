package com.dl.mapper;

import com.dl.entity.vo.TeacherVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 辅导员信息Mapper接口
 */
@Mapper
public interface TeacherMapper {
    
    /**
     * 查询辅导员列表
     * @param teacherName 辅导员姓名，支持模糊查询
     * @param college 所属学院
     * @return 辅导员列表
     */
    List<TeacherVO> queryTeacherList(@Param("teacherName") String teacherName, 
                                     @Param("college") String college);
} 