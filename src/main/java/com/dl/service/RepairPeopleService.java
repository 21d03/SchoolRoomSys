package com.dl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.RepairPeopleQueryDTO;
import com.dl.entity.vo.RepairPeopleVO;

public interface RepairPeopleService {

    /**
     * 分页查询维修人员信息
     * @param queryDTO 查询条件
     * @return 维修人员信息分页数据
     */
    IPage<RepairPeopleVO> queryRepairPeoplePage(RepairPeopleQueryDTO queryDTO);
}
