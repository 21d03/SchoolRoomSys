package com.dl.service;

import com.dl.entity.vo.SchoolIndexVO;

public interface SchoolIndexService {
    /**
     * 获取首页统计数据
     * @return 首页统计数据
     */
    SchoolIndexVO getIndexData();
} 