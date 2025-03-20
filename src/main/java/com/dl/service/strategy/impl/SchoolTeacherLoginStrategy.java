package com.dl.service.strategy.impl;

import com.dl.common.exception.BusinessException;
import com.dl.constant.JwtClaimsConstant;
import com.dl.entity.dto.LoginDTO;
import com.dl.entity.pojo.SchoolUser;
import com.dl.entity.vo.LoginVO;
import com.dl.mapper.SchoolLoginMapper;
import com.dl.properties.JwtProperties;
import com.dl.service.strategy.LoginStrategy;
import com.dl.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
public class SchoolTeacherLoginStrategy implements LoginStrategy<LoginVO> {

    @Resource
    private SchoolLoginMapper schoolLoginMapper;

    @Resource
    private JwtProperties jwtProperties;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public LoginVO login(LoginDTO loginDTO) {
        // 1.根据用户名查询用户
        SchoolUser schoolUser = schoolLoginMapper.selectUserWithDetails(loginDTO.getUserId());
        if (Objects.isNull(schoolUser)) {
            throw new BusinessException("用户名或密码错误");
        }

        // 2.密码校验
        String password = DigestUtils.md5DigestAsHex(loginDTO.getPassword().getBytes());
        if (!password.equals(schoolUser.getPassWord())) {
            throw new BusinessException("用户名或密码错误");
        }

        // 3.判断用户类型
        String level = schoolUser.getLevel();
        // 如果是普通教师登录，但level是学校管理员
        if ("2".equals(loginDTO.getUserType()) && !"0".equals(level)) {
            throw new BusinessException("用户名或密码错误");
        }
        // 如果是学校管理员登录，但level是普通教师
        if ("1".equals(loginDTO.getUserType()) && "0".equals(level)) {
            throw new BusinessException("用户名或密码错误");
        }

        // 4.生成token
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, schoolUser.getUserId());
        String token = JwtUtil.createJWT(
                jwtProperties.getSchoolSecretKey(),
                jwtProperties.getSchoolTtl(),
                claims);

        // 5.封装返回结果
        LoginVO loginVO = LoginVO.builder()
                .userId(schoolUser.getUserId())
                .userName(schoolUser.getUserName())
                .name(schoolUser.getName())
                .phone(schoolUser.getPhone())
                .level(level)
                .sex(schoolUser.getTeacherSex())
                .collegeName(schoolUser.getCollegeName())
                .token(token)
                .userType(loginDTO.getUserType())
                .build();

        return loginVO;
    }

    @Override
    public boolean supports(String userType) {
        return "2".equals(userType) || "1".equals(userType);
    }
} 