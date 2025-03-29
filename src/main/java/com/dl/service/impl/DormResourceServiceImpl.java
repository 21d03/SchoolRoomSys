package com.dl.service.impl;

import com.dl.entity.vo.DormResourceOverviewVO;
import com.dl.entity.vo.BuildingRoomDistributionVO;
import com.dl.entity.vo.BuildingUsageRateVO;
import com.dl.mapper.DormResourceMapper;
import com.dl.service.DormResourceService;
import com.dl.utils.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormResourceServiceImpl implements DormResourceService {

    private static final Logger log = LoggerFactory.getLogger(DormResourceServiceImpl.class);
    @Autowired
    private DormResourceMapper dormResourceMapper;

    @Override
    public DormResourceOverviewVO getDormResourceOverview() {
        DormResourceOverviewVO overviewVO = new DormResourceOverviewVO();
        
        // 获取宿舍楼总数
        Integer buildingCount = dormResourceMapper.getBuildingCount();
        overviewVO.setBuildingCount(buildingCount);
        
        // 获取房间总数
        Integer roomCount = dormResourceMapper.getRoomCount();
        overviewVO.setRoomCount(roomCount);
        
        // 获取床位总数
        Integer bedCount = dormResourceMapper.getBedCount();
        overviewVO.setBedCount(bedCount);
        
        // 获取已使用床位数
        Integer usedBedCount = dormResourceMapper.getUsedBedCount();
        
        // 计算使用率
        double usageRate = 0.0;
        if (bedCount != null && bedCount > 0) {
            usageRate = (double) usedBedCount / bedCount;
        }
        overviewVO.setUsageRate(usageRate);
        
        return overviewVO;
    }

    @Override
    public List<BuildingRoomDistributionVO> getBuildingRoomDistribution() {
        // 获取基础数据
        List<BuildingRoomDistributionVO> distributionList = dormResourceMapper.getBuildingRoomDistribution();
        
        // 计算已使用房间数和空闲房间数
        for (BuildingRoomDistributionVO item : distributionList) {
            String buildId = (String) ReflectionUtils.getFieldValue(item, "buildId");
            
            // 获取已使用房间数
            Integer usedRooms = dormResourceMapper.getUsedRoomCount(buildId);
            item.setUsedRooms(usedRooms);
            
            // 计算空闲房间数
            Integer totalRooms = item.getTotalRooms();
            Integer underMaintenanceRooms = item.getUnderMaintenanceRooms();
            Integer availableRooms = totalRooms - usedRooms - underMaintenanceRooms;
            item.setAvailableRooms(availableRooms);
            
            // 移除buildId字段（临时使用）
            ReflectionUtils.setFieldValue(item, "buildId", null);
        }
        
        return distributionList;
    }

    @Override
    public List<BuildingUsageRateVO> getBuildingUsageRate() {
        // 获取基础数据
        List<BuildingUsageRateVO> usageRateList = dormResourceMapper.getBuildingUsageRate();
        
        // 计算各宿舍楼使用率
        for (BuildingUsageRateVO item : usageRateList) {
            //String buildId = (String) ReflectionUtils.getFieldValue(item, "buildId");
            String buildingName = item.getBuildingName();
            String buildId = dormResourceMapper.getBuildIdByName(buildingName);
            //Integer totalRooms = (Integer) ReflectionUtils.getFieldValue(item, "totalRooms");
            Integer totalRooms = dormResourceMapper.getTotalRoomsById(buildId);
            
            // 获取已使用房间数
            Integer usedRooms = dormResourceMapper.getUsedRoomCount(buildId);
            log.info("buildId:{}已使用房间数：{}",buildId,usedRooms);
            
            // 计算使用率
            double usageRate = 0.0;
            if (totalRooms != null && totalRooms > 0) {
                usageRate = (double) usedRooms / totalRooms;
            }
            item.setUsageRate(usageRate);
            log.info("buildId:{}使用率：{}",buildId,usageRate);
            
            // 移除临时字段
            ReflectionUtils.setFieldValue(item, "buildId", null);
            ReflectionUtils.setFieldValue(item, "totalRooms", null);
        }
        
        return usageRateList;
    }
}
