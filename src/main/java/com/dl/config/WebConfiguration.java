package com.dl.config;

import com.dl.interceptor.JwtTokenSchoolInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.Resource;

/**
 * @author dongliang
 * @date 2024/09/22 15:59:59
 * @description 配置类，注册web层相关组件
 **/
@Configuration
@Slf4j
public class WebConfiguration extends WebMvcConfigurationSupport {

    @Resource
    private JwtTokenSchoolInterceptor jwtTokenSchoolInterceptor;

    /**
     * 注册自定义拦截器
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器");
        registry.addInterceptor(jwtTokenSchoolInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/login")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/doc.html");
    }

    /**
     * 跨域配置
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 设置允许的域名
        config.addAllowedOriginPattern("http://localhost:*");
        config.addAllowedOriginPattern("http://127.0.0.1:*");
        // 允许携带cookie等认证信息
        config.setAllowCredentials(true);
        // 允许所有请求方法
        config.addAllowedMethod("*");
        // 允许所有请求头
        config.addAllowedHeader("*");
        // 允许暴露的响应头
        config.addExposedHeader("*");
        
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        
        return new CorsFilter(configSource);
    }

    /**
     * 通过knife4j生成接口文档
     */
    @Bean
    public Docket docket_school() {
        log.info("生成school端接口文档");
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("宿舍管理系统接口文档")
                .version("2.0")
                .description("宿舍管理系统接口文档")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("老师端接口")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dl.controller.school"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    @Bean
    public Docket docket_housemaster() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("宿舍管理系统接口文档")
                .version("2.0")
                .description("宿舍管理系统接口文档")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("宿管端接口")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dl.controller.housemaster"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    @Bean
    public Docket docket_student() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("宿舍管理系统接口文档")
                .version("2.0")
                .description("宿舍管理系统接口文档")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("学生端接口")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dl.controller.student"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    /**
     * 设置静态资源映射
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
