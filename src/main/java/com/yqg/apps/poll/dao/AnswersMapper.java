package com.yqg.apps.poll.dao;

import com.yqg.apps.poll.bean.Answers;

import java.util.List;

import org.apache.ibatis.annotations.*;


public interface AnswersMapper {
    @Select("SELECT * FROM poll_answers WHERE id= #{id}")
    Answers findById(long id);

    @Update("UPDATE poll_answers SET selections = #{selections},checks = #{checks},content = #{content},survey_id = #{surveyId} WHERE id = #{id}")
    void update(Answers answer);

    @Insert("INSERT INTO poll_answers (selections,checks,content,survey_id) values (#{selections},#{checks},#{content},#{surveyId})")
    void insert(Answers answer);

    @Delete("DELETE FROM poll_answers WHERE id = #{id}")
    void deleteById(long id);
}