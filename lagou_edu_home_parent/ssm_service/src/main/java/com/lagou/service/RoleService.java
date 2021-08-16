package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {

    /*
    查询所有角色（或者根据条件查询所有角色）
     */
    public List<Role> findAllRole(Role role);

    public List<Integer> findMenuByRoleId(Integer id);

    /*
    为角色分配菜单
     */
    public void roleContextMenu(RoleMenuVo roleMenuVo);


    /*
    删除角色方法
     */
    public void deleteRole(Integer roleid);

}
