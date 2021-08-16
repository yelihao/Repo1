package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    @Autowired
    private CourseService courseService;

    /*
    根据课程id查询课程section和lesson
     */
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLesson(@RequestParam Integer courseId){
        List<CourseSection> courseSections = courseContentService.findSectionAndLessonByCourseId(courseId);

        ResponseResult re = new ResponseResult(true, 200, "课程内容查询成功", courseSections);

        return re;
    }


    /*
    回显章节对应的课程信息
     */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(@RequestParam Integer courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);
        ResponseResult aaaa = new ResponseResult(true, 200, "响应成功", course);
        return aaaa;
    }


    /*
    新增章节信息
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        if (courseSection.getId()==null) {
            courseContentService.saveSection(courseSection);
        }else {
            courseContentService.updateSection(courseSection);
        }

        ResponseResult responseResult = new ResponseResult(true,200,"success",null);
        return responseResult;
    }


    /*
    修改章节状态
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(@RequestParam int id,int status){
        courseContentService.updateSectionStatus(id,status);

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("status",status);
        ResponseResult re = new ResponseResult(true, 200, "success", objectObjectHashMap);
        return re;
    }

}
