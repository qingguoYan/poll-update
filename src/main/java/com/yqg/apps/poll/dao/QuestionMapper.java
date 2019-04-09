package com.yqg.apps.poll.dao;

import com.yqg.apps.poll.bean.Question;

import org.apache.ibatis.annotations.*;


public interface QuestionMapper {

    @Insert("INSERT INTO poll_question (name,questionType) values (#{name},#{questionType})")
    //保存后返回id
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void insert(Question question);

    @Update("UPDATE poll_question SET name = #{name},questionType = #{questionType} WHERE id = #{id}")
    void updateByPrimaryKey(Question question);

    @Delete("DELETE FROM poll_question WHERE id = #{id}")
    void deleteByPrimaryKey(long id);

    @Select("SELECT * FROM poll_question WHERE id = #{id}")
    Question findById(long id);

    @Select("select question.* from poll_qq qq LEFT JOIN poll_questionnaire questionnaire on qq.questionnaire_id = questionnaire.id LEFT JOIN poll_question question on qq.question_id = question.id WHERE questionnaire.id = #{id};")
    Question findByQuestionnaireId(long id);
}