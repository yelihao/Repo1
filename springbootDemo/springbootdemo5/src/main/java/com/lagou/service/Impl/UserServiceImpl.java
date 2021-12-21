package com.lagou.service.Impl;

import com.lagou.mapper.UserMapper;
import com.lagou.pojo.User;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);             //将除id所有的列都加入SQL
        userMapper.insertSelective(user);    //将user中不为空的才加入SQL --网络传递时，更快，所以优先级高
    }

    @Override
    public void deleteById(Long id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);   //同上，一般用这个
    }
}
