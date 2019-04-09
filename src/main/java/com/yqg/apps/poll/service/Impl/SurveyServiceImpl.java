package com.yqg.apps.poll.service.Impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.Page;
import com.yqg.apps.poll.dao.extend.SurveyVMMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yqg.apps.poll.bean.Survey;
import com.yqg.apps.poll.bean.extend.SurveyVM;
import com.yqg.apps.poll.dao.SurveyMapper;
import com.yqg.apps.poll.service.ISurveyService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SurveyServiceImpl implements ISurveyService{
	@Autowired
	private SurveyMapper surveyMapper;
    @Autowired
    private SurveyVMMapper surveyVMMapper;
    
    @Override
	public Page<SurveyVM> findAll() throws Exception {
		return surveyVMMapper.selectAll();
    }

	@Override
	@Transactional
	public void saveOrUpdate(Survey survey)  {
		if(survey.getId()!=null){
			surveyMapper.update(survey);
		}else{
			survey.setStatus(Survey.STATUS_INIT);
			survey.setCode("");
			Date now=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String surveyDate=sdf.format(now);
			survey.setSurveyDate(surveyDate);
			//status code surveyDate
			surveyMapper.insert(survey);
		}
		
	}

	@Override
	public void deleteById(long id) throws Exception {
		surveyMapper.deleteById(id);
	
	}

	@Override
	public Survey findSurveyById(long id) {
		return surveyMapper.findById(id);
	}


	@Override
	public SurveyVM findByIdSurveyVM(long id) throws Exception {
		return surveyVMMapper.selectByIdSurveyVM(id);
	}


	}


	

