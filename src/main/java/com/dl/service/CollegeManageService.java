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

    /**
     * 编辑学院名称
     * @param collegeId 学院ID
     * @param oldCollegeName 原学院名称
     * @param newCollegeName 新学院名称
     * @return 是否成功
     */
    boolean updateCollege(String collegeId, String oldCollegeName, String newCollegeName);
}
