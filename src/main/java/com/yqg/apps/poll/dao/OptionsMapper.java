package com.yqg.apps.poll.dao;


import com.github.pagehelper.Page;
import com.yqg.apps.poll.bean.Options;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OptionsMapper {
    @Delete("DELETE FROM poll_options WHERE id = #{id}")
    void deleteById(Long id);

    @Insert("INSERT INTO poll_options (label,name,score,question_id) values (#{label},#{name},#{score},#{questionId})")
    void insert(Options option);

    @Select("SELECT * FROM poll_options WHERE question_id = #{id}")
    @Results({
            @Result(property = "questionId",column = "question_id")
    })
    List<Options> findByQuestionId(long id);

    @Select("SELECT * FROM poll_options ")
    @Results({
            @Result(property = "questionId",column = "question_id")
    })
    Page<Options> findAll();

    @Select("SELECT * FROM poll_options WHERE id = #{id}")
    @Results({
            @Result(property = "questionId",column = "question_id")
    })
    Options findById(long id);

    @Update("UPDATE poll_options SET label = #{label},name = #{name},score = #{score},question_id = #{questionId} WHERE id = #{id}")
    void update(Options options);

    @Delete("DELETE FROM poll_options WHERE question_id = #{id}")
    void deleteByQuestionId(Long id);
}