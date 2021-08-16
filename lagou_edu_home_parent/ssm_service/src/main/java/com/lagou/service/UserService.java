package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    /*
    用户分页及多条件查询
     */
    public PageInfo findAllUserByPage(UserVo userVo);

    /*
    用户登陆（根据用户名查询用户信息）
     */
    public User login(User user) throws Exception;

    /*
    根据用户ID查询关联的角色信息，负责回显
     */
    public List<Role> findUserRelationRoleById(Integer id);

    /*
    用户关联角色
     */
    public void UserContextRole(UserVo userVo);


    /*
    获取用户权限进行菜单动态展示
     */
    public ResponseResult getUserPermissions(Integer userid);




}
