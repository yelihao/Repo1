package com.lagou.config;

import com.lagou.service.Myservice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  //标识当前类为配置类，Springboot会扫描该类，将所有标识@Bean 注解的方法的返回值注入容器
public class Myconfig {

    @Bean   //注入的名称是方法名，注入的类型是返回值类型
    public Myservice myservice(){
        return new Myservice();
    }


}
