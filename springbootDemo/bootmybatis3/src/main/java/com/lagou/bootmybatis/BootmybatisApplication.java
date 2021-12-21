package com.lagou.bootmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lagou.bootmybatis.mapper")     //指定扫描mapper的包名
public class BootmybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootmybatisApplication.class, args);
    }

}
