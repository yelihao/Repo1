package com.lagou.controller;

import com.lagou.pojo.User;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/find")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/find/{id}")
    public User findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        userService.deleteById(id);
        return "success";
    }

    @PostMapping("/insert")
    public String insert(User user){
        userService.insert(user);
        return "success";
    }

}
