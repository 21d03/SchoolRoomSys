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

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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
        // 1. 根据用户ID查询用户（包含详细信息）
        SchoolUser user = schoolLoginMapper.selectUserWithDetails(loginDTO.getUserId());
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        // 2. 校验用户类型（区分学校老师和学院老师）
        String level = user.getLevel();
        // 学校管理员登录
        if ("1".equals(loginDTO.getUserType()) && !"0".equals(level)) {
            throw new BusinessException("非学校管理员账号");
        }
        // 学院教师登录
        if ("2".equals(loginDTO.getUserType()) && "0".equals(level)) {
            throw new BusinessException("非学院教师账号");
        }

        // 3. 密码校验
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassWord())) {
            throw new BusinessException("密码错误");
        }

        // 4. 生成JWT令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getUserId());
        String token = JwtUtil.createJWT(
                jwtProperties.getSchoolSecretKey(),
                jwtProperties.getSchoolTtl(),
                claims);

        // 5. 构建返回数据
        return LoginVO.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .name(user.getName())
                .phone(user.getPhone())
                .level(user.getLevel())
                .sex(user.getTeacherSex())
                .collegeName(user.getCollegeName())
                .userType(loginDTO.getUserType())
                .token(token)
                .build();
    }

    @Override
    public boolean supports(String userType) {
        return "1".equals(userType) || "2".equals(userType);
    }
} 