package com.yqg.apps.poll.dao;

import com.yqg.apps.poll.bean.Survey;

import java.util.List;

import org.apache.ibatis.annotations.*;


public interface SurveyMapper {


    @Select("SELECT * FROM poll_survey WHERE id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "clazz_id",property = "clazzId"),
            @Result(column = "course_id",property = "courseId"),
            @Result(column = "user_id",property = "userId"),
            @Result(column = "questionnaire_id",property = "questionnaireId")
    })
    Survey findById(long id);

    @Update("UPDATE poll_survey SET average = #{average},course_id = #{courseId},clazz_id = #{clazzId},user_id = #{userId},questionnaire_id = #{questionnaireId},status = #{status},surveyDate = #{surveyDate},code = #{code} WHERE id =#{id}")
    void update(Survey survey);

    @Insert("INSERT INTO poll_survey (course_id,clazz_id,user_id,questionnaire_id,status,code,surveyDate,average) values (#{courseId},#{clazzId},#{userId},#{questionnaireId},#{status},#{code},#{surveyDate},#{average})")
    void insert(Survey survey);

    @Delete("DELETE FROM poll_survey WHERE id = #{id}")
    void deleteById(long id);

}