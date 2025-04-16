package com.dl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.dto.TeacherRepairApprovalQueryDTO;
import com.dl.entity.vo.TeacherRepairApprovalVO;
import com.dl.mapper.TeacherRepairApprovalMapper;
import com.dl.service.TeacherRepairApprovalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TeacherRepairApprovalServiceImpl implements TeacherRepairApprovalService {

    @Autowired
    private TeacherRepairApprovalMapper teacherRepairApprovalMapper;

    @Override
    public IPage<TeacherRepairApprovalVO> queryTeacherRepairApprovalPage(TeacherRepairApprovalQueryDTO queryDTO) {
        log.info("查询教师报修管理列表, 参数: {}", queryDTO);
        
        Page<TeacherRepairApprovalVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        
        return teacherRepairApprovalMapper.queryTeacherRepairApprovalPage(
                page,
                queryDTO.getTeacherId(),
                queryDTO.getStuName(),
                queryDTO.getRepairType(),
                queryDTO.getRoomId(),
                queryDTO.getItemName()
        );
    }
} 