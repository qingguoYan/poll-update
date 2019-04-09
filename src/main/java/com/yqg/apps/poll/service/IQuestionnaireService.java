package com.yqg.apps.poll.service;


import com.github.pagehelper.Page;
import com.yqg.apps.poll.bean.Questionnaire;
import com.yqg.apps.poll.bean.extend.QuestionnaireVM;


public interface IQuestionnaireService {
	//查询所有
	Page<Questionnaire> findAll() throws Exception;
	//通过id查询
	 Questionnaire findById(long id) throws Exception;

	 //单表更新
	 void update(Questionnaire questionnaire) throws Exception;

	 //通过id删除
	 void deleteById(long id) throws Exception;

    QuestionnaireVM findByIdVM(long id);

    void save(QuestionnaireVM questionnaireVM);
}
