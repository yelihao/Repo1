package com.lagou.educourseboot.service;

import com.lagou.educourseboot.entity.Course;

import java.util.List;

public interface CourseService {
    /**
     * 查询全部课程信息
     * @return
     */
    List<Course> getAllCourse();

    /**
     * 查询某门课程的详细信息
     * @param courseid 课程编号
     * @return
     */
    Course getCourseById(Integer courseid);

}
