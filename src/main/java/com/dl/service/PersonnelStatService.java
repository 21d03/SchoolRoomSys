package com.dl.service;

import com.dl.entity.vo.PersonnelOverviewVO;

public interface PersonnelStatService {

    /**
     * 获取人员总览数据
     * @return 人员总览数据
     */
    PersonnelOverviewVO getPersonnelOverview();
}
