package com.dl.service;

import com.dl.entity.vo.UnassignedHouseMasterVO;
import java.util.List;

public interface HouseMasterService {
    
    /**
     * 获取未分配宿舍楼的宿管列表
     * @return 未分配宿舍楼的宿管列表
     */
    List<UnassignedHouseMasterVO> getUnassignedHouseMasters();
} 