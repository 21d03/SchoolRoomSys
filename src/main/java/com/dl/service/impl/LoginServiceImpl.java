package com.dl.service.impl;

import com.dl.common.exception.BusinessException;
import com.dl.constant.JwtClaimsConstant;
import com.dl.entity.dto.LoginDTO;
import com.dl.entity.pojo.SchoolUser;
import com.dl.entity.vo.LoginVO;
import com.dl.entity.vo.StudentLoginVO;
import com.dl.mapper.SchoolLoginMapper;
import com.dl.mapper.StudentLoginMapper;
import com.dl.properties.JwtProperties;
import com.dl.result.Result;
import com.dl.service.LoginService;
import com.dl.utils.JwtUtil;
import com.dl.utils.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    
    @Resource
    private SchoolLoginMapper schoolLoginMapper;
    
    @Resource
    private StudentLoginMapper studentLoginMapper;

    @Resource
    private JwtProperties jwtProperties;
    
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Result<?> login(LoginDTO loginDTO) {
        String userType = loginDTO.getUserType();
        String userId = loginDTO.getUserId();
        String password = loginDTO.getPassword();
        
        switch(userType) {
            case "1": // 学校老师
            case "2": // 学院老师
                return handleTeacherLogin(userId, password, userType);
            case "3": // 学生
                return handleStudentLogin(userId, password);
            default:
                return Result.error("无效的用户类型");
        }
    }
    
    private Result<?> handleTeacherLogin(String userId, String password, String userType) {
        // 1.查询用户
        SchoolUser schoolUser = schoolLoginMapper.selectUserWithDetails(userId);
        if (Objects.isNull(schoolUser)) {
            return Result.error("用户名或密码错误");
        }

        // 2.密码校验
        if (!passwordEncoder.matches(password, schoolUser.getPassWord())) {
            return Result.error("用户名或密码错误");
        }

        // 3.判断用户类型
        String level = schoolUser.getLevel();
        // 如果是学院教师登录，但level是学校管理员
        if ("2".equals(userType) && "0".equals(level)) {
            return Result.error("用户名或密码错误");
        }
        // 如果是学校管理员登录，但level是普通教师
        if ("1".equals(userType) && !"0".equals(level)) {
            return Result.error("用户名或密码错误");
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
                .userType(userType)
                .build();

        return Result.success(loginVO);
    }
    
    private Result<?> handleStudentLogin(String userId, String password) {
        // 1.查询用户
        StudentLoginVO student = studentLoginMapper.selectStudentWithDetails(userId);
        if (Objects.isNull(student)) {
            return Result.error("用户名或密码错误");
        }

        // 2.密码校验
        if (!passwordEncoder.matches(password, student.getPassword())) {
            return Result.error("用户名或密码错误");
        }

        // 3.生成token
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, student.getUserId());
        String token = JwtUtil.createJWT(
                jwtProperties.getSchoolSecretKey(),
                jwtProperties.getSchoolTtl(),
                claims);

        // 4.设置token和userType
        student.setToken(token);
        student.setUserType("3");
        student.setPassword(null); // 清除密码

        return Result.success(student);
    }
} 