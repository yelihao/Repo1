package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    //@RequestBody :自动将请求体中的内容(json)封装成对应对象
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){
        List<Course> list = courseService.findCourseByCondition(courseVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);
        return responseResult;
    }



    // 课程图片上传
    @RequestMapping("/courseUpload")
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


    /*
        新增课程以及讲师信息
        修改课程信息与新增写在同一个方法中
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        if (courseVO.getId()==null) {
            //调用service
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "新增成功", null);
            return responseResult;
        }else {
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
            return responseResult;
        }
    }

    /*
        课程回显
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseByid(@RequestParam Integer id){

        CourseVO courseById = courseService.findCourseById(id);
        ResponseResult result = new ResponseResult(true, 200, "回显成功", courseById);
        return result;

    }


    /*
    课程状态修改
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(@RequestParam Integer id,@RequestParam Integer status){
        courseService.updateCourseStatus(id,status);

        //根据要求所以使用map
        HashMap<String, Object> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "课程状态变更成功", map);
        return result;
    }




}
