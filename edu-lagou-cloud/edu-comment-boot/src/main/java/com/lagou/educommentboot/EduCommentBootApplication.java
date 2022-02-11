package com.lagou.educommentboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.lagou.educommentboot.mapper")
public class EduCommentBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduCommentBootApplication.class, args);
    }

}
