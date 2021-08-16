package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {
        List<Menu> list = menuService.findAllMenu();
        return new ResponseResult(true,200,"success",list);
    }

    /*
    回显菜单信息
     */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(@RequestParam Integer id){
        //根据id的值，判断当前是更新还是添加 为不为-1
        if (id == -1){
            //添加操作 回显信息中就不需要查询menu信息
            List<Menu> list = menuService.findSubMenuListByPid(-1);
            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",list);
            ResponseResult res = new ResponseResult(true, 200, "新增回显成功", map);
            return res;
        }else {
            //修改操作
            Menu menu = menuService.findMenuById(id);
            List<Menu> list = menuService.findSubMenuListByPid(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",list);
            ResponseResult res = new ResponseResult(true, 200, "修改回显成功", map);
            return res;

        }
    }







}
