package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {

    /*
    用户分页以及多条件组合查询：用户名-创建日期范围
     */
    public List<User> findAllUserByPage(UserVo userVo);


    /*
    用户登陆（根据用户名查询用户信息）
     */
    public User login(User user);


    /*
    根据用户ID查询关联的角色信息
     */
    public List<Role> findUserRelationRoleById(Integer id);

    /*
    根据用户ID清空中间表
     */
    public void deleteUserContextRole(Integer userId);


    /*
    分配角色
     */
    public void userContextRole(User_Role_relation user_role_relation);


    /*
    根据角色ID，查询角色拥有的顶级菜单 parent_id=-1
     */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /*
    对父菜单PID查询子菜单信息
     */
    public List<Menu> findSubMenuByPid(Integer pid);

    /*
    获取用户拥有的资源权限信息
     */
    public List<Resource> findResourceByRoleId(List<Integer> ids);



}
