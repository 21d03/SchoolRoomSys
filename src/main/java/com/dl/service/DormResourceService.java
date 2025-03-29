package com.dl.service;

import com.dl.entity.vo.DormResourceOverviewVO;

public interface DormResourceService {

    /**
     * 获取宿舍资源总览
     * @return 宿舍资源总览数据
     */
    DormResourceOverviewVO getDormResourceOverview();
}
