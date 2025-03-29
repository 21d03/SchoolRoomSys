package com.dl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dl.entity.dto.HouseMasterQueryDTO;
import com.dl.entity.dto.HouseMasterAddDTO;
import com.dl.entity.dto.HouseMasterUpdateDTO;
import com.dl.entity.pojo.HouseMaster;
import com.dl.entity.pojo.MasterUser;
import com.dl.entity.pojo.RoomBuild;
import com.dl.entity.vo.HouseMasterVO;
import com.dl.entity.vo.UnassignedHouseMasterVO;
import com.dl.mapper.HouseMasterMapper;
import com.dl.mapper.MasterUserMapper;
import com.dl.mapper.RoomBuildMapper;
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

    @Autowired
    private MasterUserMapper masterUserMapper;

    @Autowired
    private RoomBuildMapper roomBuildMapper;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateHouseMaster(HouseMasterUpdateDTO updateDTO) {
        // 1. 检查宿管是否存在
        HouseMaster houseMaster = houseMasterMapper.selectById(updateDTO.getHmId());
        if (houseMaster == null) {
            return false;
        }
        
        // 2. 修改宿管表信息
        HouseMaster master = new HouseMaster();
        master.setHmId(updateDTO.getHmId());
        master.setHmPhone(updateDTO.getHmPhone());
        houseMasterMapper.updateById(master);
        
        // 3. 修改用户表信息
        MasterUser masterUser = masterUserMapper.selectById(updateDTO.getHmId());
        if (masterUser != null) {
            masterUser.setPhone(updateDTO.getHmPhone());
            masterUser.setIsUsed(updateDTO.getIsUsed());
            masterUserMapper.updateById(masterUser);
        }
        
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteHouseMaster(String hmId) {
        // 1. 检查宿管是否存在
        HouseMaster houseMaster = houseMasterMapper.selectById(hmId);
        if (houseMaster == null) {
            return 2; // 宿管不存在
        }
        
        // 2. 检查宿管是否分管宿舍楼
        QueryWrapper<RoomBuild> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hm_id", hmId);
        long count = roomBuildMapper.selectCount(queryWrapper);
        if (count > 0) {
            return 1; // 有分管宿舍楼，不能删除
        }
        
        // 3. 删除宿管信息
        houseMasterMapper.deleteById(hmId);
        
        // 4. 删除用户信息
        masterUserMapper.deleteById(hmId);
        
        return 0; // 删除成功
    }
}
