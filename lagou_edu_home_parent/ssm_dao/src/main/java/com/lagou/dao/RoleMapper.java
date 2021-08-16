package com.lagou.dao;

import com.lagou.domain.Menu;
import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {

    /*
    查询所有角色&按条件进行查询
    */
    public List<Role> findAllRole(Role role);


    /*
    根据角色id查询该角色关联的菜单信息id
     */
    public List<Integer> findMenuByRoleId(Integer id);


    /*
        根据roleId清空中间表关联关系
     */
    public void deleteRoleContextMenu(Integer roleId);


    /*
    为角色分配菜单信息：中间表插入信息
     */
    public void roleContextMenu(Role_menu_relation role_menu_relation);


    /*
    删除角色
     */
    public void deleteRole(Integer roleId);


}
