package com.dl.service.impl;

import com.dl.entity.pojo.HouseMaster;
import com.dl.entity.vo.UnassignedHouseMasterVO;
import com.dl.mapper.HouseMasterMapper;
import com.dl.service.IHouseMasterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 宿管信息表 服务实现类
 * </p>
 *
 * @author dongliang
 * @since 2024-11-01
 */
@Service
public class HouseMasterServiceImpl extends ServiceImpl<HouseMasterMapper, HouseMaster> implements IHouseMasterService {

    @Autowired
    private HouseMasterMapper houseMasterMapper;

    @Override
    public List<UnassignedHouseMasterVO> getUnassignedHouseMasters() {
        return houseMasterMapper.selectUnassignedHouseMasters();
    }
}
