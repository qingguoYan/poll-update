package com.yqg.apps.poll.dao;

import com.github.pagehelper.Page;
import com.yqg.apps.poll.bean.Grade;

import java.util.List;

import com.yqg.apps.poll.bean.extend.GradeVM;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


public interface GradeMapper {

    @Select("SELECT * FROM poll_grade WHERE school_id = #{id}")
    @Results({
            @Result(property = "schoolId",column = "school_id")
    })
    List<Grade> findBySchoolId(int id);

    @Select("SELECT * FROM poll_grade WHERE id = #{id}")
    @Results({
            @Result(property = "school",column = "school_id",
            one = @One(select = "com.yqg.apps.poll.dao.SchoolMapper.selectById"))
    })
    GradeVM findWithSchoolById(int id);

    @Select("SELECT * FROM poll_grade")
    @Results({
            @Result(property = "schoolId",column = "school_id")
    })
    Page<Grade> findAll();

    @Update("UPDATE poll_grade set name = #{name},description = #{description},school_id = #{schoolId} WHERE id = #{id}")
    void update(Grade grade);

    @Insert("INSERT into poll_grade (id,name,description,school_id) values (#{id},#{name},#{description},#{schoolId})")
    void insert(Grade grade);

    @Delete("DELETE FROM poll_grade WHERE id = #{id}")
    void deleteByPrimaryKey(long id);

    @Select("SELECT * FROM poll_grade WHERE id = #{id}")
    @Results({
            @Result(property = "schoolId",column = "school_id")
    })
    Grade findById(long id);
}