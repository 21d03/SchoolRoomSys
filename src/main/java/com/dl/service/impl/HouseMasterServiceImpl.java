package com.dl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dl.entity.dto.HouseMasterQueryDTO;
import com.dl.entity.dto.HouseMasterAddDTO;
import com.dl.entity.pojo.HouseMaster;
import com.dl.entity.vo.HouseMasterVO;
import com.dl.entity.vo.UnassignedHouseMasterVO;
import com.dl.mapper.HouseMasterMapper;
import com.dl.service.HouseMasterService;
import com.dl.service.IHouseMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class HouseMasterServiceImpl extends ServiceImpl<HouseMasterMapper, HouseMaster> implements IHouseMasterService, HouseMasterService {

    @Autowired
    private HouseMasterMapper houseMasterMapper;

    // 在类中添加一个BCryptPasswordEncoder实例 用于新增用户是默认密码为123456的加密
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<UnassignedHouseMasterVO> getUnassignedHouseMasters() {
        return houseMasterMapper.selectUnassignedHouseMasters();
    }

    @Override
    public IPage<HouseMasterVO> queryHouseMasterPage(HouseMasterQueryDTO queryDTO) {
        Page<HouseMasterVO> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        return houseMasterMapper.queryHouseMasterPage(
            page,
            queryDTO.getMasterId(),
            queryDTO.getMasterName(),
            queryDTO.getPhone(),
            queryDTO.getSex()
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addHouseMaster(HouseMasterAddDTO addDTO) {
        // 1. 检查宿管ID是否已存在
        HouseMaster existMaster = houseMasterMapper.selectById(addDTO.getHmId());
        if (existMaster != null) {
            return false;
        }

        // 2. 新增宿管
        HouseMaster houseMaster = new HouseMaster();
        houseMaster.setHmId(addDTO.getHmId());
        houseMaster.setHmName(addDTO.getHmName());
        houseMaster.setHmSex(addDTO.getHmSex());
        houseMaster.setHmPhone(addDTO.getHmPhone());
        
        int result = houseMasterMapper.insert(houseMaster);
        
        // 3. 如果提供了宿舍楼ID，则更新宿舍楼表
        if (result > 0 && addDTO.getBuildId() != null && !addDTO.getBuildId().isEmpty()) {
            houseMasterMapper.updateRoomBuildHmId(addDTO.getHmId(), addDTO.getBuildId());
        }

        // 4.插入用户表
        if (result > 0){
            String password = passwordEncoder.encode("123456");
            houseMasterMapper.saveMasterUser(addDTO.getHmId(),addDTO.getHmName(),password,addDTO.getHmPhone(),"1","1");
        }
        
        return result > 0;
    }
}
