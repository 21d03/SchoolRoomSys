package com.dl.service.strategy.impl;

import com.dl.common.exception.BusinessException;
import com.dl.entity.dto.LoginDTO;
import com.dl.entity.pojo.HouseMaster;
import com.dl.entity.pojo.MasterUser;
import com.dl.entity.vo.HouseMasterLoginVO;
import com.dl.mapper.HouseMasterMapper;
import com.dl.service.strategy.LoginStrategy;
import com.dl.utils.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 宿管用户登录策略实现
 */
@Slf4j
@Component("houseMasterLoginStrategy")
public class HouseMasterLoginStrategy implements LoginStrategy<HouseMasterLoginVO> {
    
    @Resource
    private HouseMasterMapper houseMasterMapper;
    
    @Resource
    private PasswordEncoder passwordEncoder;
    
    @Override
    public HouseMasterLoginVO login(LoginDTO loginDTO) {
        // 1.根据用户ID查询宿管用户
        MasterUser user = houseMasterMapper.selectMasterUserById(loginDTO.getUserId());
        if (Objects.isNull(user)) {
            throw new BusinessException("用户名或密码错误");
        }
        
        // 2.验证用户类型，必须是宿管(type=1)
        if (!"1".equals(user.getType())) {
            log.info("用户类型错误，期望类型：1，实际类型：{}", user.getType());
            throw new BusinessException("用户名或密码错误");
        }
        
        // 3.验证用户状态，如果用户被禁用（is_used=0），则不允许登录
        if (!"1".equals(user.getIsUsed())) {
            throw new BusinessException("账户已被禁用");
        }
        
        // 4.校验密码
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new BusinessException("用户名或密码错误");
        }
        
        // 5.查询宿管详细信息
        HouseMaster houseMaster = houseMasterMapper.selectHouseMasterById(loginDTO.getUserId());
        if (Objects.isNull(houseMaster)) {
            throw new BusinessException("宿管信息不存在");
        }
        
        // 6.封装返回结果
        return HouseMasterLoginVO.builder()
                .userId(houseMaster.getHmId())
                .userName(houseMaster.getHmName())
                .name(houseMaster.getHmName())
                .sex(houseMaster.getHmSex())
                .phone(houseMaster.getHmPhone())
                .buildId(houseMaster.getBuildId())
                .userType(loginDTO.getUserType())
                .build();
    }
    
    /**
     * 判断是否支持该用户类型
     * @param userType 用户类型
     * @return 是否支持
     */
    @Override
    public boolean supports(String userType) {
        return "4".equals(userType);
    }
} 