package com.dl.mapper;

import com.dl.entity.vo.ClassStudentDistributionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassStatMapper {

    /**
     * 获取班级人数分布数据
     * @param collegeId 学院ID，可为空
     * @return 班级人数分布数据
     */
    List<ClassStudentDistributionVO> getClassStudentDistribution(@Param("collegeId") String collegeId);
    
    /**
     * 获取指定专业和班级的学生人数
     * @param profession 专业名称
     * @param className 班级名称
     * @return 学生人数
     */
    Integer getStudentCountByClass(@Param("profession") String profession, @Param("className") String className);
}
