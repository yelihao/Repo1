package com.lagou.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.Md5;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {
        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());
        List<User> list = userMapper.findAllUserByPage(userVo);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;

    }

    @Override
    public User login(User user) throws Exception {
        //user2:包含了密文密码
        User user2 = userMapper.login(user);

        if (user2 != null && Md5.verify(user.getPassword(),"lagou",user2.getPassword())){
            return user2;
        }else {
            return null;
        }
    }

    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        List<Role> list = userMapper.findUserRelationRoleById(id);

        return list;
    }

    @Override
    public void UserContextRole(UserVo userVo) {
        userMapper.deleteUserContextRole(userVo.getUserId());

        List<Integer> list = userVo.getRoleIdList();
        for (Integer id : list) {
            //封装数据
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(id);
            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");
            userMapper.userContextRole(user_role_relation);
        }
    }

    //动态展示
    @Override
    public ResponseResult getUserPermissions(Integer userid) {
        //获取当前用户所拥有角色
        List<Role> rolelist = userMapper.findUserRelationRoleById(userid);
        //获取角色id 保存到list
        ArrayList<Integer> list = new ArrayList<>();
        for (Role role : rolelist) {
            list.add(role.getId());
        }
        //获取角色id的顶级菜单
        List<Menu> parentMenuByRoleId = userMapper.findParentMenuByRoleId(list);
        //父菜单关联的子菜单
        for (Menu menu : parentMenuByRoleId) {
            List<Menu> submenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(submenu);
        }
        //获取资源信息
        List<Resource> resourceByRoleId = userMapper.findResourceByRoleId(list);

        //封装数据并返回
        HashMap<String, Object> map = new HashMap<>();
        map.put("menuList",parentMenuByRoleId);
        map.put("resourceList",resourceByRoleId);

        return new ResponseResult(true,200,"success",map);
    }


}
