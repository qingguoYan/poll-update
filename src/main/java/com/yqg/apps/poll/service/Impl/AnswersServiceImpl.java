package com.yqg.apps.poll.service.Impl;



import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yqg.apps.poll.bean.Answers;
import com.yqg.apps.poll.bean.extend.AnswersVM;
import com.yqg.apps.poll.dao.AnswersMapper;
import com.yqg.apps.poll.dao.extend.AnswersVMMapper;
import com.yqg.apps.poll.service.IAnswersService;

import java.util.List;

@Service
public class AnswersServiceImpl implements IAnswersService {

	@Autowired
	private AnswersMapper answersMapper;
    @Autowired
    private AnswersVMMapper answersVMMapper;


	@Override
	public Page<AnswersVM> findAllAnswersVM() throws Exception {
		return answersVMMapper.selectAll();
	}

	@Override
	public List<Answers> findAllAnswers() throws Exception {
		return answersVMMapper.findAll();
	}

	@Override
	public Answers findById(long id) throws Exception {
		return answersMapper.findById(id);
	}

	@Override
	public void saveOrUpdate(Answers answer) throws Exception {
             if (answer.getId() == null){
             	answersMapper.insert(answer);
			 }else {
             	answersMapper.update(answer);
			 }
	}

	@Override
	public void deleteById(long id) throws Exception {
           answersMapper.deleteById(id);
	}
}
