package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


public interface CourseService {


    /*
        多条件课程列表查询
     */
    public List<Course> findCourseByCondition(CourseVO vo);

    /*
    添加课程及讲师信息
     */
    public void saveCourseOrTeacher(CourseVO vo) throws InvocationTargetException, IllegalAccessException;

    /*
    用于回显
     */
    public CourseVO findCourseById(Integer id);

    /*
    修改
     */
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    /*
    更新课程状态
     */
    public void updateCourseStatus(int id,int status);


}
