package com.dl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.RepairPeopleQueryDTO;
import com.dl.entity.vo.RepairPeopleVO;
import com.dl.entity.dto.RepairPeopleAddDTO;

public interface RepairPeopleService {

    /**
     * 分页查询维修人员信息
     * @param queryDTO 查询条件
     * @return 维修人员信息分页数据
     */
    IPage<RepairPeopleVO> queryRepairPeoplePage(RepairPeopleQueryDTO queryDTO);

    /**
     * 新增维修人员
     * @param addDTO 新增维修人员DTO
     * @return 是否成功
     */
    boolean addRepairPeople(RepairPeopleAddDTO addDTO);
}
