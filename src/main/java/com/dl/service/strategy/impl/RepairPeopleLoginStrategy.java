package com.dl.service.strategy.impl;

import com.dl.common.exception.BusinessException;
import com.dl.constant.JwtClaimsConstant;
import com.dl.entity.dto.LoginDTO;
import com.dl.entity.pojo.RepairPeople;
import com.dl.entity.vo.LoginVO;
import com.dl.mapper.RepairPeopleMapper;
import com.dl.properties.JwtProperties;
import com.dl.service.strategy.LoginStrategy;
import com.dl.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class RepairPeopleLoginStrategy implements LoginStrategy<LoginVO> {

    @Resource
    private RepairPeopleMapper repairPeopleMapper;

    @Resource
    private JwtProperties jwtProperties;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public LoginVO login(LoginDTO loginDTO) {
        // 1. 根据ID查询维修人员
        RepairPeople user = repairPeopleMapper.selectById(loginDTO.getUserId());
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        // 2. 密码校验
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new BusinessException("密码错误");
        }

        // 3. 生成JWT令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getRpId());
        String token = JwtUtil.createJWT(
                jwtProperties.getSchoolSecretKey(),
                jwtProperties.getSchoolTtl(),
                claims);

        // 4. 构建返回数据
        return LoginVO.builder()
                .userId(user.getRpId())
                .userName(user.getRpName())
                .name(user.getRpName())
                .userType(loginDTO.getUserType())
                .token(token)
                .sex(user.getRpSex())
                .phone(user.getRpPhone())
                .campus(user.getCampus())
                .build();
    }

    @Override
    public boolean supports(String userType) {
        return "5".equals(userType);
    }
} 