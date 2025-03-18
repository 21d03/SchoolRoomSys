package com.dl.service.impl;

import com.dl.constant.JwtClaimsConstant;
import com.dl.constant.MessageConstant;
import com.dl.context.BaseContext;
import com.dl.entity.dto.SchoolLoginDTO;
import com.dl.entity.pojo.SchoolUser;
import com.dl.entity.vo.SchoolLoginVO;
import com.dl.mapper.SchoolLoginMapper;
import com.dl.properties.JwtProperties;
import com.dl.result.Result;
import com.dl.service.SchoolLoginService;
import com.dl.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author dongliang
 * @date 2024/09/22 21:20:20
 * @description
 **/
@Service
@Slf4j
public class SchoolLoginServiceImpl implements SchoolLoginService {

    @Resource
    private SchoolLoginMapper schoolLoginMapper;

    @Resource
    private JwtProperties jwtProperties;

    // 在类中添加一个BCryptPasswordEncoder实例
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 用户登录方法
     *
     * @param schoolLoginDTO 登录参数对象，包含用户名和密码
     * @return 登录结果，包含用户信息和令牌
     */
    @Override
    public Result<SchoolLoginVO> login(SchoolLoginDTO schoolLoginDTO) {
        try {
            // 获取用户id和密码
            String userId = schoolLoginDTO.getUserId();
            String password = schoolLoginDTO.getPassWord();

            // 通过用户名查询数据库，获取用户信息
            SchoolUser schoolUser = schoolLoginMapper.selectById(userId);

            // 检查用户是否存在
            if (schoolUser == null) {
                return Result.error(MessageConstant.ACCOUNT_OR_PASSWORD_ERROR);
            }

            // 将输入的密码加密，以便与数据库中的密码进行比较
            if (!passwordEncoder.matches(password, schoolUser.getPassWord())) {
                return Result.error(MessageConstant.ACCOUNT_OR_PASSWORD_ERROR);
            }

            // 登录成功后，生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put(JwtClaimsConstant.USER_ID, schoolUser.getUserId());
            String token = JwtUtil.createJWT(
                    jwtProperties.getSchoolSecretKey(),
                    jwtProperties.getSchoolTtl(),
                    claims);

            //保存用户信息，用于后续条件查询
            BaseContext.setCurrentId(Long.valueOf(schoolUser.getUserId()));
            log.info("用户id：{}",BaseContext.getCurrentId());

            // 构建返回的用户信息和令牌
            SchoolLoginVO schoolLoginVO = SchoolLoginVO.builder()
                    .id(Long.valueOf(schoolUser.getUserId()))
                    .userName(schoolUser.getUserName())
                    .name(schoolUser.getName())
                    .token(token)
                    .build();
            return Result.success(schoolLoginVO);
        } catch (Exception e) {
            // 记录错误日志，并返回错误信息
            log.error("登录失败: {}", e.getMessage());
            return Result.error(e.getMessage());
        }
    }

}
