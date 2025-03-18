package com.dl.service.strategy.impl;

import com.dl.common.exception.BusinessException;
import com.dl.constant.JwtClaimsConstant;
import com.dl.entity.dto.LoginDTO;
import com.dl.entity.vo.StudentLoginVO;
import com.dl.mapper.StudentLoginMapper;
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
public class StudentLoginStrategy implements LoginStrategy<StudentLoginVO> {

    @Resource
    private StudentLoginMapper studentLoginMapper;

    @Resource
    private JwtProperties jwtProperties;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public StudentLoginVO login(LoginDTO loginDTO) {
        // 1. 根据学号查询学生（包含详细信息）
        StudentLoginVO student = studentLoginMapper.selectStudentWithDetails(loginDTO.getUserId());
        if (student == null) {
            throw new BusinessException("用户不存在");
        }

        // 2. 密码校验
        if (!passwordEncoder.matches(loginDTO.getPassword(), student.getPassword())) {
            throw new BusinessException("密码错误");
        }

        // 3. 生成JWT令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, student.getUserId());
        String token = JwtUtil.createJWT(
                jwtProperties.getSchoolSecretKey(),
                jwtProperties.getSchoolTtl(),
                claims);

        // 4. 设置token和用户类型
        student.setToken(token);
        student.setUserType(loginDTO.getUserType());
        
        return student;
    }

    @Override
    public boolean supports(String userType) {
        return "3".equals(userType);
    }
} 