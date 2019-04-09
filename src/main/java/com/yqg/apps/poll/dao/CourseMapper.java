package com.yqg.apps.poll.dao;

import com.github.pagehelper.Page;
import com.yqg.apps.poll.bean.Course;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


public interface CourseMapper {

    @Select("SELECT * FROM poll_course")
    Page<Course> findAll();

    @Select("SELECT * FROM poll_course WHERE id = #{id}")
    Course findById(long id);

    @Select("UPDATE poll_course SET name = #{name},period = #{period},description = #{description} WHERE id = #{id}")
    void update(Course course);

    @Select("INSERT INTO poll_course (name,period,description) values (#{name},#{period},#{description})")
    void insert(Course course);

    @Delete("DELETE FROM poll_course WHERE id = #{id}")
    void deleteById(long id);
}