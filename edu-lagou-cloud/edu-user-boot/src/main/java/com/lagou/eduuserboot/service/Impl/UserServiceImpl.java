package com.lagou.eduuserboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lagou.eduuserboot.entity.User;
import com.lagou.eduuserboot.mapper.UserMapper;
import com.lagou.eduuserboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String helloRobbin() {
        return restTemplate.getForEntity("http://service",String.class).getBody();
    }


    @Override
    public User login(String phone, String password) {
        // 创建条件构造
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        // 编写具体的查询条件
        queryWrapper.eq("phone", phone);
        queryWrapper.eq("password", password);
        // 调用mp的查询方法selectOne
        User user = userMapper.selectOne(queryWrapper);

        return user;
    }

    @Override
    public Integer checkPhone(String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("phone", phone);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            return 0;
        }
        return 1;
    }

    @Override
    public Integer register(String phone, String password, String nickname, String headimg) {
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        user.setName(nickname);
        user.setPortrait(headimg);
        userMapper.insert(user);
        return null;
    }


}
