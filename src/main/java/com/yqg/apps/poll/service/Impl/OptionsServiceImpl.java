package com.yqg.apps.poll.service.Impl;



import com.github.pagehelper.Page;
import com.yqg.apps.poll.dao.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yqg.apps.poll.bean.Options;

import com.yqg.apps.poll.dao.OptionsMapper;
import com.yqg.apps.poll.service.IOptionsService;
@Service
public class OptionsServiceImpl implements IOptionsService{
  @Autowired
  private OptionsMapper optionsMapper;
  @Autowired
  private QuestionMapper questionMapper;

	@Override
	public Page<Options> findAll() throws Exception {
		return optionsMapper.findAll();
	}

	@Override
	public Options findById(long id) throws Exception {
		return optionsMapper.findById(id);
	}

	@Override
	public void saveOrUpdate(Options options) throws Exception {
           if(options.getId() == null){
           	   optionsMapper.insert(options);
		   }else {
           	   optionsMapper.update(options);
		   }
	}

	@Override
	public void deleteById(long id) throws Exception {
             optionsMapper.deleteById(id);
	}



}
