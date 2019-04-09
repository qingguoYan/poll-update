package com.yqg.apps.poll.service;



import com.github.pagehelper.Page;

import com.yqg.apps.poll.bean.extend.QuestionVM;

public interface IQuestionService {
	//查找所有问题，并查找出问题的所有选项
	 Page<QuestionVM> findAll() throws Exception;
	 
	 //更新或者保存问题，并保存更新问题选项
	 void saveOrUpdateQuestionVM(QuestionVM questionVM) throws Exception;
	 
	 //删除问题，级联删除选项
	 void deleteById(long id) throws Exception;

	

}
