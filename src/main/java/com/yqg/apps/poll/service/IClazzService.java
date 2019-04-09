package com.yqg.apps.poll.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.yqg.apps.poll.bean.Clazz;
import com.yqg.apps.poll.bean.extend.ClazzVM;

public interface IClazzService {
	/*
	 *查询所有 
	 */
	Page<Clazz> findAll() throws Exception;
	Page<ClazzVM> findAllClazzVM() throws Exception;
	void saveOrUpdate(Clazz clazz) throws Exception;
	ClazzVM selectById(long id)throws Exception;
	/*
	 *关键字查询
	 */
	List<ClazzVM> query(String keywords) throws Exception;
	/*
	 * 
	 */
	
	void deleteById(long id)throws Exception;
	void batchDelete(List<Long> ids)throws Exception;

}
