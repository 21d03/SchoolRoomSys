package com.dl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.context.BaseContext;
import com.dl.entity.dto.LeaveApprovalQueryDTO;
import com.dl.entity.vo.LeaveApprovalVO;
import com.dl.entity.vo.TeacherIndexVO;
import com.dl.mapper.TeacherIndexMapper;
import com.dl.result.PageResult;
import com.dl.service.TeacherIndexService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教师首页数据统计Service实现类
 */
@Service
public class TeacherIndexServiceImpl implements TeacherIndexService {
    
    @Resource
    private TeacherIndexMapper teacherIndexMapper;
    
    @Override
    public TeacherIndexVO getIndexData(String teacherId) {
        
        // 获取教师管理的学生总数
        Integer studentCount = teacherIndexMapper.getStudentCount(teacherId);
        
        // 获取教师待审批请假数
        Integer pendingLeaveCount = teacherIndexMapper.getPendingLeaveCount(teacherId);
        
        // 获取教师请假审批总数
        Integer totalLeaveCount = teacherIndexMapper.getTotalLeaveCount(teacherId);
        
        // 构建返回数据
        return TeacherIndexVO.builder()
                .studentCount(studentCount)
                .pendingLeaveCount(pendingLeaveCount)
                .totalLeaveCount(totalLeaveCount)
                .build();
    }
    
    @Override
    public PageResult<LeaveApprovalVO> getRecentLeaveApprovals(String teacherId, LeaveApprovalQueryDTO queryDTO) {
        // 创建分页对象
        Page<LeaveApprovalVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        
        // 调用Mapper查询
        IPage<LeaveApprovalVO> pageResult = teacherIndexMapper.getRecentLeaveApprovals(page, teacherId);
        
        // 封装返回结果
        return new PageResult<>(pageResult.getTotal(), pageResult.getRecords());
    }
}