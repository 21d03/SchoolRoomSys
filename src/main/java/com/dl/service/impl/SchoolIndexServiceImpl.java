package com.dl.service.impl;

import com.dl.entity.vo.SchoolIndexVO;
import com.dl.mapper.SchoolIndexMapper;
import com.dl.service.SchoolIndexService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SchoolIndexServiceImpl implements SchoolIndexService {
    
    @Resource
    private SchoolIndexMapper schoolIndexMapper;
    
    @Override
    public SchoolIndexVO getIndexData() {
        // 获取宿舍楼统计数据
        Integer buildingTotal = schoolIndexMapper.getBuildingTotal();
        Integer buildingNormal = schoolIndexMapper.getBuildingNormal();
        Integer buildingStopped = schoolIndexMapper.getBuildingStopped();
        
        // 获取房间统计数据
        Integer roomTotal = schoolIndexMapper.getRoomTotal();
        Integer roomNormal = schoolIndexMapper.getRoomNormal();
        Integer roomStopped = schoolIndexMapper.getRoomStopped();
        
        // 获取报修审批统计数据
        Integer repairTotal = schoolIndexMapper.getRepairTotal();
        Integer repairProcessed = schoolIndexMapper.getRepairProcessed();
        Integer repairPending = schoolIndexMapper.getRepairPending();
        
        // 获取请假审批统计数据
        Integer leaveTotal = schoolIndexMapper.getLeaveTotal();
        Integer leaveProcessed = schoolIndexMapper.getLeaveProcessed();
        Integer leavePending = schoolIndexMapper.getLeavePending();
        
        // 计算总审批数据
        Integer approvalTotal = repairTotal + leaveTotal;
        Integer approvalProcessed = repairProcessed + leaveProcessed;
        Integer approvalPending = repairPending + leavePending;
        
        // 构建返回数据
        return SchoolIndexVO.builder()
                .buildingTotal(buildingTotal)
                .buildingNormal(buildingNormal)
                .buildingStopped(buildingStopped)
                .roomTotal(roomTotal)
                .roomNormal(roomNormal)
                .roomStopped(roomStopped)
                .approvalTotal(approvalTotal)
                .approvalProcessed(approvalProcessed)
                .approvalPending(approvalPending)
                .build();
    }
} 