package com.dl.utils;

import org.springframework.util.DigestUtils;

/**
 * 密码工具类
 */
public class PasswordUtil {

    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD = "123456";

    /**
     * 密码加密
     * @param password 原始密码
     * @return 加密后的密码
     */
    public static String encrypt(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
} 