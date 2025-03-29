package com.dl.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dl.entity.dto.RepairPeopleAddDTO;
import com.dl.entity.dto.RepairPeopleQueryDTO;
import com.dl.entity.dto.RepairPeopleUpdateDTO;
import com.dl.entity.pojo.MasterUser;
import com.dl.entity.pojo.RepairPeople;
import com.dl.entity.vo.RepairPeopleVO;
import com.dl.mapper.MasterUserMapper;
import com.dl.mapper.RepairPeopleMapper;
import com.dl.service.RepairPeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private MasterUserMapper masterUserMapper;

    // 在类中添加一个BCryptPasswordEncoder实例 用于新增用户是默认密码为123456的加密
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addRepairPeople(RepairPeopleAddDTO addDTO) {
        // 1. 检查维修人员ID是否已存在
        RepairPeople existRepairPeople = repairPeopleMapper.selectByIdToAdd(addDTO.getRpId());
        if (existRepairPeople != null) {
            return false;
        }
        
        // 检查master_user表是否存在该ID
        MasterUser existUser = masterUserMapper.selectById(addDTO.getRpId());
        if (existUser != null) {
            return false;
        }
        
        // 2. 新增维修人员信息
        RepairPeople repairPeople = new RepairPeople();
        repairPeople.setRpId(addDTO.getRpId());
        repairPeople.setRpName(addDTO.getRpName());
        repairPeople.setRpSex(addDTO.getRpSex());
        repairPeople.setRpPhone(addDTO.getRpPhone());
        repairPeople.setCampus(addDTO.getCampus());
        
        int result = repairPeopleMapper.insert(repairPeople);
        if (result <= 0) {
            return false;
        }
        
        // 3. 新增用户信息
        MasterUser masterUser = new MasterUser();
        masterUser.setUserId(addDTO.getRpId());
        masterUser.setName(addDTO.getRpName());
        masterUser.setPhone(addDTO.getRpPhone());
        masterUser.setPassword(passwordEncoder.encode("123456"));
        masterUser.setType("2"); // 2表示维修人员
        masterUser.setIsUsed(addDTO.getIsUsed());
        
        return masterUserMapper.insert(masterUser) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRepairPeople(RepairPeopleUpdateDTO updateDTO) {
        // 1. 检查维修人员是否存在
        RepairPeople existRepairPeople = repairPeopleMapper.selectByIdToAdd(updateDTO.getRpId());
        if (existRepairPeople == null) {
            return false;
        }
        
        // 2. 修改维修人员表信息
        RepairPeople repairPeople = new RepairPeople();
        repairPeople.setRpId(updateDTO.getRpId());
        repairPeople.setRpPhone(updateDTO.getRpPhone());
        repairPeopleMapper.updateById(repairPeople);
        
        // 3. 修改用户表信息
        MasterUser masterUser = masterUserMapper.selectById(updateDTO.getRpId());
        if (masterUser != null) {
            masterUser.setPhone(updateDTO.getRpPhone());
            masterUser.setIsUsed(updateDTO.getIsUsed());
            masterUserMapper.updateById(masterUser);
        }
        
        return true;
    }
}
