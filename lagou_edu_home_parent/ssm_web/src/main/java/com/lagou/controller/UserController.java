package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){
        PageInfo allUserByPage = userService.findAllUserByPage(userVo);
        ResponseResult res = new ResponseResult(true, 200, "success", allUserByPage);
        return res;

    }


    //用户登陆
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User user1 = userService.login(user);
        if (user1!=null){
            //保存用户id 以及access_token到session中
            HttpSession session = request.getSession();
            String s = UUID.randomUUID().toString();
            session.setAttribute("access_token",s);
            session.setAttribute("user_id",user1.getId());

            //将查询到的信息响应前台
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("access_token",s);
            map.put("user_id",user1.getId());
            return new ResponseResult(true,200,"登陆成功",map);
        }else {
            return new ResponseResult(true,400,"用户名或者密码错误",null);
        }
    }

    /*
    分配角色（回显）
     */
    @RequestMapping("/findUserRelationRoleById")
    public ResponseResult findUserRelationRoleById(Integer id){

        List<Role> list = userService.findUserRelationRoleById(id);
        return new ResponseResult(true,200,"success",list);
    }


    /*
    分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult UserContextRole(@RequestBody UserVo userVo){
        userService.UserContextRole(userVo);
        return new ResponseResult(true,200,"succe",null);
    }

    //获取用户权限动态展示
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        //1.获取请求头中的token
        String header_token = request.getHeader("Authorization");
        //2.获取session中的token
        String accecc_token =(String) request.getSession().getAttribute("accecc_token");
        if (header_token.equals(accecc_token)){
            //在登陆状态
            Integer id =(Integer) request.getSession().getAttribute("user_id");
            ResponseResult rr = userService.getUserPermissions(id);
            return rr;
        }else{
            return new ResponseResult(false,400,"failure",null);
        }

    }




}
