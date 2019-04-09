package com.yqg.apps.poll.dao;


import com.yqg.apps.poll.bean.QuestionnaireQuestion;
import org.apache.ibatis.annotations.Insert;


public interface QuestionnaireQuestionMapper {
    @Insert("insert into poll_qq (questionnaire_id,question_id) values (#{questionnaireId},#{questionId})")
    void insert(QuestionnaireQuestion qq);
}