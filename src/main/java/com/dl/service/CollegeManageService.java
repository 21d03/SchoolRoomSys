package com.dl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.CollegeQueryDTO;
import com.dl.entity.vo.CollegeVO;

public interface CollegeManageService {

    /**
     * 分页查询学院列表
     */
    IPage<CollegeVO> queryCollegePage(CollegeQueryDTO queryDTO);

    /**
     * 新增学院
     */
    boolean addCollege(String collegeId, String collegeName);

    /**
     * 删除学院
     * @return 0-成功，1-有学生，2-有教师
     */
    int deleteCollege(String collegeId, String collegeName);
}
