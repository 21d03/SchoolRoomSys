package com.dl.service.impl;

import com.dl.entity.vo.StudentIndexVO;
import com.dl.mapper.StudentIndexMapper;
import com.dl.service.StudentIndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudentIndexServiceImpl implements StudentIndexService {

    @Autowired
    private StudentIndexMapper studentIndexMapper;

    @Override
    public StudentIndexVO getStudentIndexData(String studentId) {
        log.info("获取学生首页数据统计, 学生ID: {}", studentId);
        
        StudentIndexVO indexVO = new StudentIndexVO();
        
        // 获取学生宿舍信息
        indexVO.setBuildName(studentIndexMapper.getStudentBuildName(studentId));
        indexVO.setRoomId(studentIndexMapper.getStudentRoomId(studentId));
        indexVO.setBedId(studentIndexMapper.getStudentBedId(studentId));
        
        // 获取请假审批统计
        indexVO.setLeavePendingCount(studentIndexMapper.getLeavePendingCount(studentId));
        indexVO.setLeaveProcessedCount(studentIndexMapper.getLeaveProcessedCount(studentId));
        
        // 获取报修申请统计
        indexVO.setRepairPendingCount(studentIndexMapper.getRepairPendingCount(studentId));
        indexVO.setRepairProcessedCount(studentIndexMapper.getRepairProcessedCount(studentId));
        
        return indexVO;
    }
} 