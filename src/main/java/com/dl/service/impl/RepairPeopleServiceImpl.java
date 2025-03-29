package com.dl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.dto.RepairPeopleQueryDTO;
import com.dl.entity.vo.RepairPeopleVO;
import com.dl.mapper.RepairPeopleMapper;
import com.dl.service.RepairPeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 维修人员信息表 服务实现类
 * </p>
 *
 * @author dongliang
 * @since 2024-11-01
 */
@Service
public class RepairPeopleServiceImpl implements RepairPeopleService {

    @Autowired
    private RepairPeopleMapper repairPeopleMapper;

    @Override
    public IPage<RepairPeopleVO> queryRepairPeoplePage(RepairPeopleQueryDTO queryDTO) {
        Page<RepairPeopleVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        return repairPeopleMapper.queryRepairPeoplePage(
            page,
            queryDTO.getRpId(),
            queryDTO.getRpName(),
            queryDTO.getRpSex(),
            queryDTO.getRpPhone(),
            queryDTO.getCampus()
        );
    }
}
