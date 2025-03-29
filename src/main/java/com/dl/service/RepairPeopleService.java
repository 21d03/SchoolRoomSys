package com.dl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dl.entity.dto.RepairPeopleQueryDTO;
import com.dl.entity.vo.RepairPeopleVO;
import com.dl.entity.dto.RepairPeopleAddDTO;
import com.dl.entity.dto.RepairPeopleUpdateDTO;

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

    /**
     * 修改维修人员信息
     * @param updateDTO 修改信息DTO
     * @return 是否修改成功
     */
    boolean updateRepairPeople(RepairPeopleUpdateDTO updateDTO);

    /**
     * 删除维修人员
     * @param rpId 维修人员ID
     * @return 删除结果：0-成功，1-删除失败，2-维修人员不存在
     */
    int deleteRepairPeople(String rpId);
}
