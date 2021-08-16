package com.lagou.service.Impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.Role_menu_relation;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> list = roleMapper.findAllRole(role);
        return list;
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer id) {
        List<Integer> lis = roleMapper.findMenuByRoleId(id);
        return lis;
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {
        Integer roleId = roleMenuVo.getRoleId();
        roleMapper.deleteRoleContextMenu(roleId);

        List<Integer> list = roleMenuVo.getMenuIdList();
        for (Integer menuid : list) {
            Role_menu_relation rmr = new Role_menu_relation();
            rmr.setMenuId(menuid);
            rmr.setRoleId(roleId);

            rmr.setCreatedBy("system");
            Date date = new Date();
            rmr.setCreatedTime(date);
            rmr.setUpdatedTime(date);
            rmr.setUpdatedby("system");
            roleMapper.roleContextMenu(rmr);
        }



    }

    @Override
    public void deleteRole(Integer roleid) {
        roleMapper.deleteRoleContextMenu(roleid);
        roleMapper.deleteRole(roleid);
    }
}
