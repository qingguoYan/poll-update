package com.yqg.apps.poll.service.Impl;

import java.util.List;

import com.github.pagehelper.Page;
import com.yqg.apps.poll.dao.extend.QuestionVMMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yqg.apps.poll.bean.Options;
import com.yqg.apps.poll.bean.Question;
import com.yqg.apps.poll.bean.extend.QuestionVM;
import com.yqg.apps.poll.dao.OptionsMapper;
import com.yqg.apps.poll.dao.QuestionMapper;
import com.yqg.apps.poll.service.IQuestionService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class QuestionServiceImpl implements IQuestionService {
	@Autowired
	private QuestionVMMapper questionVMMapper;
	@Autowired
	private OptionsMapper optionsMapper;
	@Autowired
	private QuestionMapper questionMapper;
	@Override
	public Page<QuestionVM> findAll() throws Exception {
		
		return questionVMMapper.selectAll();
	}
	/*
	 * 保存或者修改问题，带选项
	 */
	@Override
	@Transactional
	public void saveOrUpdateQuestionVM(QuestionVM questionVM) throws Exception {
       /*
        * 1.得到question和options实体，因为保存是分别保存的
        * 2.判断是否是保存还是修改操作
        * 3.判断题目类型(简答还是选择)
        *     
        */
		
		Question question=new Question();
		question.setId(questionVM.getId());
		question.setName(questionVM.getName());
		question.setQuestionType(questionVM.getQuestionType());
		List<Options> options=questionVM.getOptions();
              if(questionVM.getId()==null){
			/*保存操作
			 * 1.保存题目
			 * 2.保存选型
			 */
	            	  if(questionVM.getQuestionType().equals("简答题")){
	            		  //仅仅保存题目
	            	      questionMapper.insert(question);
	            	  }else{
	            		 //先保存题目
	            		  questionMapper.insert(question);
	            		  //获取question的id
	            		  long id=question.getId();          		  
	            		 //保存选项
	            		for(Options option : options){
	            			//遍历所有选项，进行保存
	            			option.setQuestionId(id);
	            			optionsMapper.insert(option);
	            		}
	            	  }
            	  
	          	}else{
	          		//修改
	          		/*
	          		 * 1.修改题目信息
	          		 * 2.判断类型
	          		 * 2.删除选项
	          		 * 3.重新添加选项
	          		 */

	          		if(questionVM.getQuestionType().equals("简答题")){
						questionMapper.updateByPrimaryKey(question);
					}else {
						questionMapper.updateByPrimaryKey(question);
						for(Options bean : options){
							optionsMapper.deleteByQuestionId(questionVM.getId());
						}
						long id=question.getId();
						for(Options option : options){
							//遍历所有选项，进行保存
							option.setQuestionId(id);
							optionsMapper.insert(option);
						}
					}
		     }   
		
	}
	@Override
	public void deleteById(long id) throws Exception {
		
		questionMapper.deleteByPrimaryKey(id);
	}


}
