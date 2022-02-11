package com.lagou.eduuserboot.controller;

import com.lagou.eduuserboot.entity.User;
import com.lagou.eduuserboot.entity.UserDTO;
import com.lagou.eduuserboot.feign.UserFeign;
import com.lagou.eduuserboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserTest {


    @Autowired // 远程消费
    private UserService userService;

    @Autowired
    private UserFeign userFeign;

    @GetMapping("login")
    public UserDTO login(String phone, String password, String nickname, String headimg) {
        UserDTO dto = new UserDTO();
        System.out.println("草泥马");
        User user = null;
        System.out.println("phone = " + phone);
        System.out.println("password = " + password);
        System.out.println("nickname = " + nickname);

        // 检测手机号是否注册
        Integer i = userService.checkPhone(phone);
        if(i == 0){
            // 未注册，自动注册并登录
            userService.register(phone, password,nickname,headimg);
            dto.setMessage("手机号尚未注册，系统已帮您自动注册，请牢记密码！");
            user = userService.login(phone, password);
        }else{
            user = userService.login(phone, password);
            if(user == null){
                dto.setState(300); //300表示失败
                dto.setMessage("帐号密码不匹配，登录失败！");
            }else{
                dto.setState(200); //200表示成功
                dto.setMessage("登录成功！");
            }
        }

        dto.setContent(user);
        return dto;
    }


}
