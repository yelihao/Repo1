package com.lagou.bootmybatis.mapper;

import com.lagou.bootmybatis.entity.Course;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

public interface CourseMapper {

    /*
    Id return lesson
     */
    @Select("select * from course where id = #{id}")
    Course findById(String id);




}
