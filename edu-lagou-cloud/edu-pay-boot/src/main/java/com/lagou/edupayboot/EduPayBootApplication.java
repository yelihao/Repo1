package com.lagou.edupayboot;

import org.apache.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = {SpringBootConfiguration.class}) //屏蔽使用原来spring.shardingsphere配置项
@EnableEurekaClient // 注册到中心的客户端
@MapperScan("com.lagou.edupayboot.mapper")
public class EduPayBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduPayBootApplication.class, args);
    }

}
