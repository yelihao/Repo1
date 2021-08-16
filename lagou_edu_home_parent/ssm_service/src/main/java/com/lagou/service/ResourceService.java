package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;

import java.util.List;

public interface ResourceService {


    /*
    对资源表进行分页 并且进行多条件组合查询
     */
    public PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo);







}
