package com.lagou.bootmybatis;

import com.lagou.bootmybatis.mapper.CourseMapper;
import com.lagou.bootmybatis.pojo.Course;
import com.lagou.bootmybatis.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BootmybatisApplicationTests {

    @Autowired
    private CourseMapper mapper;


    @Test
    void contextLoads() {
        Course byId = mapper.findById(7);
        System.out.println(byId);
    }

    //写入 key：1 value：mysql数据库中id为1的course
    @Autowired
    private RedisUtils redisUtils;

    @Test
    void writeRedis(){
        redisUtils.set("1",mapper.findById(1));
        System.out.println("success");
    }

    @Test
    void readRedis(){
        Course o =(Course) redisUtils.get("1");
        System.out.println(o);
    }



}
