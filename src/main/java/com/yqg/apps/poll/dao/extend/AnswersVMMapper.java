package com.yqg.apps.poll.dao.extend;

import com.github.pagehelper.Page;
import com.yqg.apps.poll.bean.Answers;
import com.yqg.apps.poll.bean.extend.AnswersVM;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface AnswersVMMapper {
    @Select("SELECT * FROM poll_answers")
	@Results({
			@Result(id = true,property = "id",column = "id"),
			@Result(property = "survey",column = "survey_id",
			one = @One(select = "com.yqg.apps.poll.dao.SurveyMapper.findById"))
	})
	Page<AnswersVM> selectAll() throws Exception;

	@Select("SELECT * FROM poll_answers")
	@Results({
			@Result(id = true,property = "id",column = "id"),
			@Result(column = "survey_id",property = "surveyId")
	})
	List<Answers> findAll();
}
