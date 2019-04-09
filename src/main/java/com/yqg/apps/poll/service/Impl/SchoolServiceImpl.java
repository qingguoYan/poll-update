package com.yqg.apps.poll.service.Impl;
import java.util.List;

import com.github.pagehelper.Page;
import com.yqg.apps.poll.bean.extend.SchoolVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.yqg.apps.poll.bean.School;
import com.yqg.apps.poll.dao.SchoolMapper;
import com.yqg.apps.poll.service.ISchoolService;

import javax.annotation.Resource;

@Service
public class SchoolServiceImpl implements ISchoolService{
@Autowired
private SchoolMapper schoolMapper;
	@Override
	public Page<School> findAll()  {
		return schoolMapper.findAll();
	}
	@Override
	public School findById(long id) {
	return schoolMapper.selectById(id);
	}

	public List<School> query(String keywords) {
		return schoolMapper.query("%"+keywords+"%");
	}

	@Override
	public void saveOrupdate(School school)  {
		if(school.getId()!=null){
			schoolMapper.update(school);
			
		}else{
			schoolMapper.insert(school);
		}
		
	}

	@Override
	public void deleteById(long id)  {
		schoolMapper.deleteById(id);
		
	}

	@Override
	/**
	 * 级联删除
	 */
	public void batchDelete(List<Long> ids)  {
		for(long id:ids){
			schoolMapper.deleteById(id);
		}
	}

	@Override
	public SchoolVM findWithGrade(int id) {
         return schoolMapper.findWithGrade(id);
	}

}

