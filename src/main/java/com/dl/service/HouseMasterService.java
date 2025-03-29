package com.dl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.HouseMasterQueryDTO;
import com.dl.entity.dto.HouseMasterAddDTO;
import com.dl.entity.vo.HouseMasterVO;
import com.dl.entity.vo.UnassignedHouseMasterVO;

import java.util.List;

public interface HouseMasterService {

    /**
     * 分页查询宿管信息
     * @param queryDTO 查询条件
     * @return 宿管信息分页数据
     */
    IPage<HouseMasterVO> queryHouseMasterPage(HouseMasterQueryDTO queryDTO);

    /**
     * 新增宿管
     * @param addDTO 新增宿管DTO
     * @return 是否成功
     */
    boolean addHouseMaster(HouseMasterAddDTO addDTO);

    List<UnassignedHouseMasterVO> getUnassignedHouseMasters();
}