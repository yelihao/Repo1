package com.lagou;

import com.lagou.controller.HelloController;
import com.lagou.pojo.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

//SpringBoot的测试环境
@RunWith(SpringRunner.class)  //@RunWith():运行器   SpringJUnit4ClassRunner.class Spring的运行环境  junit：junit测试环境
@SpringBootTest //该注解是 标记了当前类为SpringBoot测试类，加载项目的ApplicationContext上下文环境
class Springbootdemo2ApplicationTests {
    /*
    需求：调用HelloController的hello方法
     */
    @Autowired
    private HelloController helloController;


    @Test
    void contextLoads() {
        String hello = helloController.hello();
        System.out.println(hello);
    }

    @Autowired
    private Person person;

    @Test
    void showPersonInfo(){
        System.out.println(person);
    }

    @Autowired
    private ApplicationContext applicationContext;
    @Test
    public void testConfig(){
        System.out.println(applicationContext.containsBean("myservice"));
    }

}
