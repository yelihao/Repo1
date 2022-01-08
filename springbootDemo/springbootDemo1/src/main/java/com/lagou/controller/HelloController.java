package com.lagou.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //返回jasn串
@RequestMapping("Hello")
public class HelloController {

    @RequestMapping("/boot")
    public String helloBoot(){
        return "Hello Spring boot";
    }


}
