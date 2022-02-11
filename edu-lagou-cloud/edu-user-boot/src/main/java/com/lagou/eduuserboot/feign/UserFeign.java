package com.lagou.eduuserboot.feign;

import com.lagou.eduuserboot.entity.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/*
自定义的Feign接口,调用User接口都在此定义
 */
@FeignClient(name = "edu-user-boot")
public interface UserFeign {

    /**
     *
     * @param phone 登陆电话
     * @param password  密码
     * @return
     */
    @GetMapping("/user/login")
    public UserDTO login(String phone, String password, String nickname, String headimg);
}
