package com.yqg.apps.poll.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.yqg.apps.poll.bean.Grade;
import com.yqg.apps.poll.bean.extend.GradeVM;

public interface IGradeService {
	
	
	/*
	 *查询所有 
	 */
	Page<Grade> findAll() throws Exception;

	Page<GradeVM> findAllGradeVM(Integer pageNo,Integer pageSize) throws Exception;

	GradeVM findById(long id)throws Exception;
	/*
	 *关键字查询
	 */
	List<GradeVM> query(String keywords) throws Exception;

	void saveOrUpdate(Grade grade) throws Exception;
	void deleteById(long id)throws Exception;
	void batchDelete(List<Long> ids)throws Exception;

    List<Grade> findBySchoolId(int id)throws Exception;

    GradeVM findWithSchoolById(int id)throws Exception;

	List<Grade> batchFind(List<Long> ids);
}
