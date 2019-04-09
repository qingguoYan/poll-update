package com.yqg.apps.poll.dao;

import com.github.pagehelper.Page;
import com.yqg.apps.poll.bean.User;

import org.apache.ibatis.annotations.*;

import java.util.List;


public interface UserMapper {

    @Select("SELECT * FROM poll_user WHERE id = #{id}")
    User findById(long id);

    @Select("SELECT * FROM poll_user")
    Page<User> selectAll();

    @Select("DELETE FROM poll_user WHERE id = #{id}")
    void deleteByPrimaryKey(long id);

    @Update("UPDATE poll_user SET name = #{name},gender = #{gender},birth = #{birth},hiredate = #{hiredate},type = #{type} WHERE id = #{id}")
    void update(User user);

    @Insert("INSERT INTO poll_user (name,gender,birth,hiredate,type) values (#{name},#{gender},#{birth},#{hiredate},#{type})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void insert(User user);
}