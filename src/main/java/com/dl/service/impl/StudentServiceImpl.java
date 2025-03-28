package com.dl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.dto.StudentQueryDTO;
import com.dl.entity.vo.StudentVO;
import com.dl.entity.LeaveApproval;
import com.dl.entity.RepairApproval;
import com.dl.mapper.*;
import com.dl.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 学生信息 服务实现类
 * </p>
 *
 * @author dongliang
 * @since 2024-11-01
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RoomInfoMapper roomInfoMapper;

    @Autowired
    private LeaveApprovalMapper leaveApprovalMapper;

    @Autowired
    private RepairApprovalMapper repairApprovalMapper;

    @Autowired
    private StudentUserMapper studentUserMapper;

    @Override
    public IPage<StudentVO> queryStudentPage(StudentQueryDTO queryDTO) {
        Page<StudentVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        return studentMapper.queryStudentPage(page, 
            queryDTO.getStuId(), 
            queryDTO.getStuName(), 
            queryDTO.getCollege(), 
            queryDTO.getSex());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteStudent(String stuId) {
        try {
            // 1. 删除学生基本信息
            studentMapper.deleteById(stuId);
            
            // 2. 删除学生用户信息
            // Assuming studentUserMapper.deleteById(stuId) is called elsewhere in the code
            studentUserMapper.deleteById(stuId);
            // 3. 删除宿舍房间信息
            roomInfoMapper.deleteRoomInfoByStuId(stuId);
            
            // 4. 删除请假记录
            LambdaQueryWrapper<LeaveApproval> leaveWrapper = new LambdaQueryWrapper<>();
            leaveWrapper.eq(LeaveApproval::getStudentId, stuId);
            leaveApprovalMapper.delete(leaveWrapper);
            
            // 5. 删除报修记录
            LambdaQueryWrapper<RepairApproval> repairWrapper = new LambdaQueryWrapper<>();
            repairWrapper.eq(RepairApproval::getStudentId, stuId);
            repairApprovalMapper.delete(repairWrapper);
            
            return true;
        } catch (Exception e) {
            throw new RuntimeException("删除学生信息失败", e);
        }
    }
} 