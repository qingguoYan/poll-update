package com.yqg.apps.poll.bean.extend;

import com.yqg.apps.poll.bean.Course;

//import Questionnaire;
import com.yqg.apps.poll.bean.Questionnaire;
import com.yqg.apps.poll.bean.User;

public class SurveyVM {

	private Long id;
	private String status;
	private String code;
	private String surveyDate;
	private Course course;
	private ClazzVM clazzVM;
	private User user;
	private QuestionnaireVM questionnaireVM;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSurveyDate() {
		return surveyDate;
	}
	public void setSurveyDate(String surveyDate) {
		this.surveyDate = surveyDate;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public ClazzVM getClazzVM() {
		return clazzVM;
	}
	public void setClazzVM(ClazzVM clazzVM) {
		this.clazzVM = clazzVM;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public QuestionnaireVM getQuestionnaireVM() {
		return questionnaireVM;
	}

	public void setQuestionnaireVM(QuestionnaireVM questionnaireVM) {
		this.questionnaireVM = questionnaireVM;
	}
}
