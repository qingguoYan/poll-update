package com.yqg.apps.poll.dao;

import com.github.pagehelper.Page;
import com.yqg.apps.poll.bean.Questionnaire;
import com.yqg.apps.poll.bean.extend.QuestionnaireVM;
import org.apache.ibatis.annotations.*;


public interface QuestionnaireMapper {
    @Select("SELECT * FROM poll_questionnaire")
    Page<Questionnaire> findAll();

    @Select("SELECT * FROM poll_questionnaire WHERE id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "questions",column = "id",
            many = @Many(select = "com.yqg.apps.poll.dao.QuestionMapper.findByQuestionnaireId"))
    })
    Questionnaire findById(long id);

    @Update("UPDATE poll_questionnaire SET poll_questionnaire name = #{name},description = #{description} WHERE id = #{id}")
    void update(Questionnaire questionnaire);

    @Delete("DELETE FROM poll_questionnaire WHERE id = #{id}")
    void delete(long id);

    @Select("SELECT * FROM poll_questionnaire WHERE id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "questionVMs",
            many = @Many(select = "com.yqg.apps.poll.dao.extend.QuestionVMMapper.findByQuestionnaireId"))
    })
    QuestionnaireVM findByIdVM(long id);

    @Insert("INSERT INTO poll_questionnaire (name,description) values (#{name},#{description})")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    void save(Questionnaire questionnaire);
}