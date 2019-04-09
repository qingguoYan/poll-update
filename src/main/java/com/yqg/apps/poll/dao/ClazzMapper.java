package com.yqg.apps.poll.dao;

import com.github.pagehelper.Page;
import com.yqg.apps.poll.bean.Clazz;

import java.util.List;

import org.apache.ibatis.annotations.*;


public interface ClazzMapper {

    @Select("SELECT id ,name,description,grade_id,charge_id FROM poll_clazz")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "chargeId",column = "charge_id"),
            @Result(property = "gradeId",column = "grade_id")
    })
    Page<Clazz> selectAll();

    @Update("UPDATE poll_clazz set id = #{id},name = #{name},description = #{description},charge_id = #{chargeId" +
            "" +
            "},grade_id = #{gradeId} WHERE id = #{id}")
    void update(Clazz clazz);

    @Insert("INSERT INTO poll_clazz (name,description,grade_id,charge_id) values (#{name},#{description},#{gradeId},#{chargeId})")
    void insert(Clazz clazz);

    @Delete("DELETE FROM poll_clazz WHERE id = #{id}")
    void deleteByPrimaryKey(long id);

    @Select("SELECT * FROM poll_clazz WHERE charge_id = #{id}")
    Clazz findById(Integer id);
}