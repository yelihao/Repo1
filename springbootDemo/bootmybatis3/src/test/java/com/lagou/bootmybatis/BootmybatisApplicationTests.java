package com.lagou.bootmybatis;

import com.lagou.bootmybatis.entity.User;
import com.lagou.bootmybatis.mapper.CourseMapper;
import com.lagou.bootmybatis.entity.Course;
import com.lagou.bootmybatis.mapper.UserMapper;
import com.lagou.bootmybatis.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootmybatisApplicationTests {

    @Autowired
    private CourseMapper courseMappermapper;

    @Test
    public void contextLoads() {
        Course c = courseMappermapper.findById("7");
        System.out.println(c);
    }

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findUserbyId() {
        User user = userMapper.queryById(100030011);
        System.out.println(user);
    }


    //写入 key：1 value：mysql数据库中id为7的course
    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void writeRedis(){
        redisUtils.set("1",courseMappermapper.findById("7"));
        System.out.println("success");
    }

    @Test
    public void readRedis(){
        Course o =(Course) redisUtils.get("1");
        System.out.println(o);
    }



}
