package com.yqg.apps.poll.bean;

public class Survey {
	//课调创建成功后的状态
	public static final String STATUS_INIT="未开启";
	//点击开启按钮，意味着当前可执行课调code
	public static final String STATUS_BEGIN="开启";
	//点击关闭按钮意味着课调关闭
	public static final String STATUS_CHECK_UN="未审核";
	public static final String STATUS_CHECK_PASS="审核通过";
	public static final String STATUS_CHECK_NOPASS="审核不通过";
	

    private Long id;

    private String status;

    private String code;

    private Double average;

    private String surveyDate;

    private Long courseId;

    private Long clazzId;

    private Long userId;

    private Long questionnaireId;

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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


    public String getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(String surveyDate) {
        this.surveyDate = surveyDate;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getClazzId() {
        return clazzId;
    }

    public void setClazzId(Long clazzId) {
        this.clazzId = clazzId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Long questionnaireId) {
        this.questionnaireId = questionnaireId;
    }
}