package com.dl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.ClassInfoQueryDTO;
import com.dl.entity.vo.ClassInfoVO;

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
} 