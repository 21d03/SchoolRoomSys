package com.dl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.vo.LeaveApprovalVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 教师首页数据统计Mapper接口
 */
@Mapper
public interface TeacherIndexMapper {
    
    /**
     * 获取教师管理的学生总数
     * @param teacherId 教师ID
     * @return 学生总数
     */
    @Select("SELECT COUNT(*) FROM student_info WHERE teacher_id = #{teacherId}")
    Integer getStudentCount(@Param("teacherId") String teacherId);
    
    /**
     * 获取教师待审批请假数
     * @param teacherId 教师ID
     * @return 待审批请假数
     */
    @Select("SELECT COUNT(*) FROM leave_approval WHERE teacher_id = #{teacherId} AND status = '0'")
    Integer getPendingLeaveCount(@Param("teacherId") String teacherId);
    
    /**
     * 获取教师请假审批总数
     * @param teacherId 教师ID
     * @return 请假审批总数
     */
    @Select("SELECT COUNT(*) FROM leave_approval WHERE teacher_id = #{teacherId}")
    Integer getTotalLeaveCount(@Param("teacherId") String teacherId);
    
    /**
     * 查询教师最近请假审批记录
     * @param page 分页参数
     * @param teacherId 教师ID
     * @return 请假审批记录
     */
    IPage<LeaveApprovalVO> getRecentLeaveApprovals(Page<LeaveApprovalVO> page, @Param("teacherId") String teacherId);
}