package com.lagou.service.Impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;


    @Override
    public List<Course> findCourseByCondition(CourseVO vo) {
        return courseMapper.findCourseByCondition(vo);
    }

    @Override
    public void saveCourseOrTeacher(CourseVO vo) throws InvocationTargetException, IllegalAccessException {

        //从vo中分别封装course和teacher
        //封装课程信息
        Course course = new Course();
        //注意是apache的包 不要导错包
        //把vo中和course相同名称的复制过去
        BeanUtils.copyProperties(course,vo);

        //补全课程信息
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);

        courseMapper.saveCourse(course);
        //获取新插入数据的ID值 通过xml配置了对应标签
        int courseId = course.getId();

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,vo);

        //补全讲师信息
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setCourseId(courseId);
        teacher.setIsDel(0);

        courseMapper.saveTeacher(teacher);
    }

    @Override
    public CourseVO findCourseById(Integer id) {
        CourseVO courseById = courseMapper.findCourseById(id);
        return courseById;
    }

    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        Course course = new Course();
        Teacher teacher = new Teacher();

        BeanUtils.copyProperties(course,courseVO);
        BeanUtils.copyProperties(teacher,courseVO);

        //前台页面的信息封装，但是有更新时间 需要手动更新。需要进行补全信息
        Date date = new Date();
        course.setUpdateTime(date);
        int courseId = course.getId();
        courseMapper.updateCourse(course);

        // 千万记得要设置ID，可以看看DAO 这个ID是很重要的

        teacher.setCourseId(courseId);
        teacher.setUpdateTime(date);
        courseMapper.updateTeacher(teacher);
    }

    @Override
    public void updateCourseStatus(int id, int status) {

        Course course = new Course();
        course.setId(id);
        course.setStatus(status);
        Date date = new Date();
        course.setUpdateTime(date);
        courseMapper.updateCourseStatus(course );

    }


}
