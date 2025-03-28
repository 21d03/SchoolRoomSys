package com.dl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.ClassInfo;
import com.dl.entity.vo.ClassInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
} 