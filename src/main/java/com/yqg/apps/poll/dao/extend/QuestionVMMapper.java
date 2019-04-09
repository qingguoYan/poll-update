package com.yqg.apps.poll.dao.extend;



import com.github.pagehelper.Page;
import com.yqg.apps.poll.bean.extend.QuestionVM;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;


/*
 * 一对多
 */

public interface QuestionVMMapper {
    @Select("SELECT * FROM poll_question")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(property = "options",column = "id",
            many = @Many(select = "com.yqg.apps.poll.dao.OptionsMapper.findByQuestionId"))
    })
    Page<QuestionVM> selectAll() throws Exception;

    @Select("SELECT question.* FROM poll_qq qq left join poll_questionnaire questionnaire on qq.questionnaire_id = questionnaire.id left join poll_question question on qq.question_id = question.id WHERE questionnaire.id = #{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(property = "options",column = "id",
            many = @Many(select = "com.yqg.apps.poll.dao.OptionsMapper.findByQuestionId"))
    })
    QuestionVM findByQuestionnaireId (long id) throws Exception;
}
