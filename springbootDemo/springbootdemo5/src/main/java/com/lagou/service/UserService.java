package com.lagou.service;

import com.lagou.pojo.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    void insert(User user);

    void deleteById(Long id);

    void update(User user);

}
