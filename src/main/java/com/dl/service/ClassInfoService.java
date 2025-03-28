package com.dl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.ClassInfoQueryDTO;
import com.dl.entity.vo.ClassInfoVO;
import com.dl.entity.vo.UnassignedClassVO;
import com.dl.entity.dto.AssignClassDTO;
import com.dl.entity.dto.UnassignClassDTO;

import java.util.List;

/**
 * 班级信息服务接口
 */
public interface ClassInfoService {

    /**
     * 分页查询班级信息
     * @param queryDTO 查询条件
     * @return 班级信息分页数据
     */
    IPage<ClassInfoVO> queryClassInfoPage(ClassInfoQueryDTO queryDTO);

    /**
     * 查询未分配辅导员的班级
     * @param collegeName 学院名称
     * @return 未分配辅导员的班级列表
     */
    List<UnassignedClassVO> queryUnassignedClasses(String collegeName);

    /**
     * 分配班级辅导员
     * @param assignClassDTO 分配班级DTO
     * @return 是否分配成功
     */
    boolean assignClass(AssignClassDTO assignClassDTO);

    /**
     * 取消班级分管
     * @param unassignClassDTO 取消分管班级DTO
     * @return 是否取消成功
     */
    boolean unassignClass(UnassignClassDTO unassignClassDTO);
} 