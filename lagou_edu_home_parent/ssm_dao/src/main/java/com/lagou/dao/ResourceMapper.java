package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;

import java.util.List;

public interface ResourceMapper {


    /*
    对资源表进行分页 并且进行多条件组合查询
     */
    public List<Resource> findAllResourceByPage(ResourceVo resourceVo);






}
