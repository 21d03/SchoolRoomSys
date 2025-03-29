package com.dl.service.impl;

import com.dl.entity.vo.DormResourceOverviewVO;
import com.dl.mapper.DormResourceMapper;
import com.dl.service.DormResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DormResourceServiceImpl implements DormResourceService {

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
}
