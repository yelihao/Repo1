package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    /*
    分页查询广告
     */
    @RequestMapping("/findAllPromotionAdByPage")
    //因为不是请求体 所以不需要@RequestBody
    public ResponseResult findAllAdByPage(PromotionAdVo promotionAdVo){
        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVo);
        ResponseResult res = new ResponseResult(true, 200, "success", pageInfo);
        return res;
    }


    // 图片上传
    @RequestMapping("/PromotionAdUpload")
    //声明参数名称时需要与前端参数统一，或者使用@RequestParam注解
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        //1.判断接收到的上传文件是否为空
        if (file.isEmpty()){
            //为空则抛出异常
            throw new RuntimeException();
        }
        //2.获取项目部署路径（SSM在tmocat上的路径）,
        // user/apache-tomcat-8.5.56/webapps/ssm-web/   我们想要保存图片到webapps下的一个叫upload文件下
        String realPath = request.getServletContext().getRealPath("/");
        String substring = realPath.substring(0, realPath.indexOf("ssm-web"));//截取过后的路径

        //3.获取原文件名  //lagou.jpg     -----用来获取后缀名
        String originalFilename = file.getOriginalFilename();

        //4.生成新文件名  //12312421.jpg
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //5.文件上传到服务器
        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath, newFileName);

        //如果目录不存在就创建目录
        if (!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录:"+filePath);
        }

        //图片进行了真正的上传
        file.transferTo(filePath);

        //6.将文件名与文件路径返回，进行响应
        HashMap<String,String > map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload" + newFileName);
        ResponseResult re = new ResponseResult(true, 200, "图片上传成功", map);
        return re;
    }


    //广告动态上下线
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(Integer id,Integer status){
        promotionAdService.updatePromotionAdStatus(id,status);
        ResponseResult res = new ResponseResult(true, 200, "success", null);
        return res;
    }




}
