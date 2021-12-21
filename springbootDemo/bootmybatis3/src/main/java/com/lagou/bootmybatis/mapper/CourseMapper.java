package com.lagou.bootmybatis.mapper;

import com.lagou.bootmybatis.pojo.Course;
import org.apache.ibatis.annotations.Select;

public interface CourseMapper {

    /*
    Id return lesson
     */
    @Select("select * from course where id = #{id}")
    Course findById(Integer id);






}
