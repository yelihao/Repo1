package com.lagou;


/*
Spring Boot的启动累类
    通常放在二级包中，比如放在com.lagou中
    因为springboot在做包扫描时，会扫描启动类所在的包及其其子包下的所有内容
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//该注解 标识了当前类为Spring Boot项目的启动类
@SpringBootApplication
public class SpringBootDemo1Application {

    public static void main(String[] args) {
        //样本代码，即所有springboot的启动类都一样
        SpringApplication.run(SpringBootDemo1Application.class,args);

    }

}
