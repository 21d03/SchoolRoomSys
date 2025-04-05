package com.dl.mapper;

import com.dl.entity.vo.DormStudentVO;
import com.dl.entity.vo.TeacherDormVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 教师宿舍查询Mapper接口
 */
@Mapper
public interface TeacherDormMapper {
    
    /**
     * 查询教师管理的学生所住的宿舍
     * @param teacherId 教师ID
     * @return 宿舍列表
     */
    List<TeacherDormVO> queryTeacherDorms(@Param("teacherId") String teacherId);
    
    /**
     * 查询宿舍学生信息
     * @param buildId 楼栋ID
     * @param roomId 房间号
     * @return 学生列表
     */
    List<DormStudentVO> queryDormStudents(@Param("buildId") String buildId, @Param("roomId") String roomId);
    
    /**
     * 查询宿舍类型（几人间）
     * @param buildId 楼栋ID
     * @param roomId 房间号
     * @return 宿舍类型
     */
    String queryRoomType(@Param("buildId") String buildId, @Param("roomId") String roomId);
} 