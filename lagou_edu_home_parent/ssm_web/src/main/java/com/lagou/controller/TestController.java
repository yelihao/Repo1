package com.lagou.controller;


import com.lagou.domain.Test;
import com.lagou.service.Impl.TestServiceImpl;
import com.lagou.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController     //组合了@Controller和@ResponseBody
//        当方法上面没有写ResponseBody,底层会将方法的返回值封装为ModelAndView对象。
//        如果返回值是字符串，那么直接将字符串写到客户端；如果是一个对象，会将对象转化为json串，然后写到客户端。
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/findAllTest")
    public List<Test>findAllTest(){
        return testService.findAllTest();
    }


}
