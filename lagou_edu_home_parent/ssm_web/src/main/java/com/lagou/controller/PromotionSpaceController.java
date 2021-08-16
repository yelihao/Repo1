package com.lagou.controller;


import com.lagou.domain.PromotionSpace;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;

    /*
    查询所有广告位
     */
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult  findAllPromotionSpace(){
        List<PromotionSpace> list = promotionSpaceService.findAllPromotionSpace();
        ResponseResult res = new ResponseResult(true, 200, "响应成功", list);
        return res;
    }

    /*
    添加广告位
     */
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace){
        if (promotionSpace.getId()==null) {
            promotionSpaceService.savePromotionSpace(promotionSpace);
        }else {
            promotionSpaceService.updatePromotionSpace(promotionSpace);
        }
        return new ResponseResult(true,200,"success",null);
    }


    /*
    回显广告位,即根据id查询广告位置
     */
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(@RequestParam int id){
        PromotionSpace promotionSpace = promotionSpaceService.findPromotionSpaceById(id);
        ResponseResult rs = new ResponseResult(true, 200, "success", promotionSpace);
        return rs;
    }








}
