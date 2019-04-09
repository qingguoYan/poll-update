package com.yqg.apps.poll.service;



import com.github.pagehelper.Page;
import com.yqg.apps.poll.bean.Answers;
import com.yqg.apps.poll.bean.extend.AnswersVM;

import java.util.List;


public interface IAnswersService {


	Page<AnswersVM> findAllAnswersVM() throws Exception;

	List<Answers> findAllAnswers() throws Exception;

	Answers findById(long id) throws Exception;

	 void saveOrUpdate(Answers answer) throws Exception;

	 void deleteById(long id) throws Exception;

}
