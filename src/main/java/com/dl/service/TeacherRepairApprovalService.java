package com.dl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.TeacherRepairApprovalQueryDTO;
import com.dl.entity.vo.TeacherRepairApprovalVO;

public interface TeacherRepairApprovalService {
    
    /**
     * 查询教师管理的学生报修记录
     * @param queryDTO 查询参数
     * @return 分页结果
     */
    IPage<TeacherRepairApprovalVO> queryTeacherRepairApprovalPage(TeacherRepairApprovalQueryDTO queryDTO);
} 