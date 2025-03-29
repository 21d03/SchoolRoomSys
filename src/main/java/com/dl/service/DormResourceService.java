package com.dl.service;

import com.dl.entity.vo.BuildingRoomDistributionVO;
import com.dl.entity.vo.DormResourceOverviewVO;
import com.dl.entity.vo.BuildingUsageRateVO;

import java.util.List;

public interface DormResourceService {

    /**
     * 获取宿舍资源总览
     * @return 宿舍资源总览数据
     */
    DormResourceOverviewVO getDormResourceOverview();

    /**
     * 获取各宿舍楼房间分布情况
     * @return 各宿舍楼房间分布情况
     */
    List<BuildingRoomDistributionVO> getBuildingRoomDistribution();

    /**
     * 获取各宿舍楼使用率
     * @return 各宿舍楼使用率
     */
    List<BuildingUsageRateVO> getBuildingUsageRate();
}
