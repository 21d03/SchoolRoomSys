package com.dl.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.vo.LeaveRecordVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LeaveRecordMapper {
    
    /**
     * 分页查询学生请假记录
     * @param page 分页参数
     * @param studentId 学生ID
     * @param status 审批状态
     * @param startTimeBegin 开始时间范围(起始)
     * @param startTimeEnd 开始时间范围(结束)
     * @param reason 请假原因
     * @return 分页结果
     */
    IPage<LeaveRecordVO> queryLeaveRecordPage(
            Page<LeaveRecordVO> page,
            @Param("studentId") String studentId,
            @Param("status") String status,
            @Param("startTimeBegin") String startTimeBegin,
            @Param("startTimeEnd") String startTimeEnd,
            @Param("reason") String reason
    );
} 