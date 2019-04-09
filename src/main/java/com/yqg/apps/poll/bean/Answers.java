package com.yqg.apps.poll.bean;


import io.swagger.annotations.ApiModelProperty;

public class Answers {

    private Long id;

    @ApiModelProperty("单选题答案eg:5|3|4")
    private String selections;// 单选题eg:5|3|4
    @ApiModelProperty("多选题答案eg:3.5|2,3")
    private String checks; //多选题:3.5|2,3
    @ApiModelProperty("简答题答案eg:aop|ioc")
    private String content;//简答题:..../.....

    private Long surveyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSelections() {
        return selections;
    }

    public void setSelections(String selections) {
        this.selections = selections;
    }

    public String getChecks() {
        return checks;
    }

    public void setChecks(String checks) {
        this.checks = checks;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }
}