package com.dl.service;

import com.dl.common.Result;
import com.dl.entity.vo.HmIndexVO;

/**
 * 宿管首页数据统计Service接口
 */
public interface HmIndexService {
    
    /**
     * 获取宿管首页统计数据
     * @param hmId 宿管ID
     * @param buildId 宿舍楼ID
     * @return 宿管首页统计数据
     */
    Result getIndexData(String hmId, String buildId);
}