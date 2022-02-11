package com.lagou.educourseboot.service.Impl;

import com.lagou.educourseboot.entity.Course;
import com.lagou.educourseboot.mapper.CourseMapper;
import com.lagou.educourseboot.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;


    @Override
    public List<Course> getAllCourse() {
        return courseMapper.getAllCourse();
    }

    @Override
    public Course getCourseById(Integer courseid) {
        return getCourseById(courseid);
    }
}
