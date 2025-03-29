package com.dl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.ClassAddDTO;
import com.dl.entity.dto.ClassQueryDTO;
import com.dl.entity.dto.ClassUpdateDTO;
import com.dl.entity.vo.ClassVO;

public interface ClassManageService {

    /**
     * 分页查询班级列表
     */
    IPage<ClassVO> queryClassPage(ClassQueryDTO queryDTO);

    /**
     * 新增班级
     */
    boolean addClass(ClassAddDTO addDTO);

    /**
     * 编辑班级
     */
    boolean updateClass(ClassUpdateDTO updateDTO);

    /**
     * 删除班级
     * @return 0-成功，1-有教师，2-有学生
     */
    int deleteClass(String profession, String className);
}
