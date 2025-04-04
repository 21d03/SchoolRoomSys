package com.dl.service;

import com.dl.entity.dto.LeaveApprovalQueryDTO;
import com.dl.entity.vo.LeaveApprovalVO;
import com.dl.entity.vo.TeacherIndexVO;
import com.dl.result.PageResult;

/**
 * 教师首页数据统计Service接口
 */
public interface TeacherIndexService {
    /**
     * 获取教师首页统计数据
     * @return 教师首页统计数据
     */
    TeacherIndexVO getIndexData(String teacherId);
    
    /**
     * 获取教师最近请假审批记录
     * @param teacherId 教师ID
     * @param queryDTO 查询参数
     * @return 请假审批记录分页数据
     */
    PageResult<LeaveApprovalVO> getRecentLeaveApprovals(String teacherId, LeaveApprovalQueryDTO queryDTO);
}