package com.yqg.apps.poll.service;




import com.github.pagehelper.Page;
import com.yqg.apps.poll.bean.Survey;
import com.yqg.apps.poll.bean.extend.SurveyVM;

public interface ISurveyService {
 
	Page<SurveyVM> findAll()throws Exception;
	//级联查询
	SurveyVM findByIdSurveyVM(long id)throws Exception;


	void saveOrUpdate(Survey survey) throws Exception;
	
	void deleteById(long id)throws Exception;

    Survey findSurveyById(long id);

}
