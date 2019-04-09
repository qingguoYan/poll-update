package com.yqg.apps.poll.dao.extend;



import com.github.pagehelper.Page;
import com.yqg.apps.poll.bean.extend.SurveyVM;
import org.apache.ibatis.annotations.*;


public interface SurveyVMMapper {

	@Select("SELECT * FROM poll_survey")
	@Results({
			@Result(id = true,column = "id",property = "id"),
			@Result(property = "course",column = "course_id",
			one = @One(select = "com.yqg.apps.poll.dao.CourseMapper.findById")),
			@Result(property = "clazzVM",column = "clazz_id",
			one = @One(select = "com.yqg.apps.poll.dao.extend.ClazzVMMapper.selectByPrimaryKey")),
			@Result(property = "user",column = "user_id",
			one = @One(select = "com.yqg.apps.poll.dao.UserMapper.findById")),
			@Result(property = "questionnaireVM",column = "questionnaire_id",
			one = @One(select = "com.yqg.apps.poll.dao.QuestionnaireMapper.findByIdVM"))
	})
	Page<SurveyVM> selectAll();

	@Select("SELECT * FROM poll_survey WHERE id = #{id}")
	@Results({
			@Result(id = true,column = "id",property = "id"),
			@Result(property = "course",column = "course_id",
					one = @One(select = "com.yqg.apps.poll.dao.CourseMapper.findById")),
			@Result(property = "clazzVM",column = "clazz_id",
					one = @One(select = "com.yqg.apps.poll.dao.extend.ClazzVMMapper.selectByPrimaryKey")),
			@Result(property = "user",column = "user_id",
					one = @One(select = "com.yqg.apps.poll.dao.UserMapper.findById")),
			@Result(property = "questionnaireVM",column = "questionnaire_id",
					one = @One(select = "com.yqg.apps.poll.dao.QuestionnaireMapper.findByIdVM"))
	})
	SurveyVM selectByIdSurveyVM(long id);




}
