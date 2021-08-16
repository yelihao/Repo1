package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseSection;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CourseContentService {

    //根据课程id 查询课程内容：章节+课时
    public List<CourseSection> findSectionAndLessonByCourseId(Integer id);

    public Course findCourseByCourseId(int id);

    public void saveSection(CourseSection courseSection);

    public void updateSection(CourseSection courseSection);

    /*
    修改章节状态
     */
    public void updateSectionStatus(int id,int status);


}
