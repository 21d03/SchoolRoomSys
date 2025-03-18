package com.dl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.dl.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SchoolRoomSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolRoomSysApplication.class, args);
    }

}
