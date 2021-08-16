package com.lagou.dao;

import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceMapper {


    //获取所有的广告位
    public List<PromotionSpace> findAllPromotionSpace();


    //添加广告位
    public void savePromotionSpace(PromotionSpace promotionSpace);

    //修改广告位
    public void updatePromotionSpace(PromotionSpace promotionSpace);


    //回显广告位，根据ID找广告位
    public PromotionSpace findPromotionSpaceById(int id);





}
