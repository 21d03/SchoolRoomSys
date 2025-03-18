package com.dl.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "dl.jwt")
@Data
public class JwtProperties {

    /**
     * 老师端生成jwt令牌相关配置
     */
    private String schoolSecretKey;
    private long schoolTtl;
    private String schoolTokenName;


}
