package com.yqg.apps.poll.dao;

import com.github.pagehelper.Page;
import com.yqg.apps.poll.bean.School;

import java.util.List;

import com.yqg.apps.poll.bean.extend.SchoolVM;
import org.apache.ibatis.annotations.*;


public interface SchoolMapper {
    
    @Select("SELECT id, name, logoPath, description, address, telephone, copyright FROM poll_school")
    Page<School> findAll();

    @Select("SELECT id, name, logoPath, description, address, telephone, copyright FROM poll_school WHERE name like #{s}")
    List<School> query(String s);

    @Select("SELECT id, name, logoPath, description, address, telephone, copyright FROM poll_school WHERE id = #{id}")
    School selectById(long id);

    @Insert("insert  into poll_school ( name, logoPath, address, telephone, copyright, description) values (#{name}, #{logopath},#{address}, #{telephone}, #{copyright},#{description})")
    void insert(School school);

    @Update("UPDATE poll_school set name = #{name} ,logoPath = #{logopath},address = #{address},telephone = #{telephone},copyright = #{copyright},description = #{description}WHERE id = #{id}")
    void update(School school);

    @Delete("DELETE FROM poll_school WHERE id = #{id}")
    void deleteById(long id);

    @Select("SELECT * FROM poll_school WHERE id = #{id}")
    @Results({
            //column="id",id是传入进来的参数id
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "grades",column = "id",
            many = @Many(select = "com.yqg.apps.poll.dao.GradeMapper.findBySchoolId"))
    })
    SchoolVM findWithGrade(int id);
}