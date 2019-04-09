package com.yqg.apps.poll.service.Impl;


import com.github.pagehelper.Page;
import com.yqg.apps.poll.bean.Options;
import com.yqg.apps.poll.bean.Question;
import com.yqg.apps.poll.bean.QuestionnaireQuestion;
import com.yqg.apps.poll.bean.extend.QuestionVM;
import com.yqg.apps.poll.bean.extend.QuestionnaireVM;
import com.yqg.apps.poll.dao.OptionsMapper;
import com.yqg.apps.poll.dao.QuestionMapper;
import com.yqg.apps.poll.dao.QuestionnaireQuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yqg.apps.poll.bean.Questionnaire;
import com.yqg.apps.poll.dao.QuestionnaireMapper;
import com.yqg.apps.poll.service.IQuestionnaireService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionnaireServiceImpl implements IQuestionnaireService{
    @Autowired
    private QuestionnaireMapper questionnnaireMapper;

    @Autowired
	private QuestionMapper questionMapper;

    @Autowired
	private OptionsMapper optionsMapper;

    @Autowired
	private QuestionnaireQuestionMapper questionnaireQuestionMapper;

	@Override
	public Page<Questionnaire> findAll() throws Exception {
		return questionnnaireMapper.findAll();
	}

	@Override
	public Questionnaire findById(long id) throws Exception {
		return questionnnaireMapper.findById(id);
	}

	@Override
	public void update(Questionnaire questionnaire) throws Exception {
         questionnnaireMapper.update(questionnaire);
	}

	@Override
	public void deleteById(long id) throws Exception {
         questionnnaireMapper.delete(id);
	}

	@Override
	public QuestionnaireVM findByIdVM(long id) {
		return questionnnaireMapper.findByIdVM(id);
	}

	@Override
	@Transactional
	public void save(QuestionnaireVM questionnaireVM) {
		Questionnaire questionnaire = new Questionnaire();
		questionnaire.setDescription(questionnaireVM.getDescription());
		questionnaire.setName(questionnaireVM.getName());
		questionnnaireMapper.save(questionnaire);

		List<QuestionVM> list = questionnaireVM.getQuestionVMs();
		for(QuestionVM questionVM :list){
			if(questionVM.getQuestionType().equals("简答题")){
				Question question = new Question();
				question.setName(questionVM.getName());
				question.setQuestionType(questionVM.getQuestionType());
				questionMapper.insert(question);

				QuestionnaireQuestion qq = new QuestionnaireQuestion();
				qq.setQuestionId(question.getId());
				qq.setQuestionnaireId(questionnaire.getId());
				questionnaireQuestionMapper.insert(qq);

			}else {
				Question question = new Question();
				question.setQuestionType(questionVM.getQuestionType());
				question.setName(questionVM.getName());
				questionMapper.insert(question);

				QuestionnaireQuestion qq = new QuestionnaireQuestion();
				qq.setQuestionId(question.getId());
				qq.setQuestionnaireId(questionnaire.getId());
				questionnaireQuestionMapper.insert(qq);

				List<Options> options = questionVM.getOptions();
				for (Options bean : options){
					Options option = new Options();
					option.setQuestionId(question.getId());
					option.setLabel(bean.getLabel());
					option.setName(bean.getName());
					option.setScore(bean.getScore());
					optionsMapper.insert(option);
				}
			}
		}
	}


}
