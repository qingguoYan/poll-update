package com.yqg.apps.poll.service;

import com.github.pagehelper.Page;
import com.yqg.apps.poll.bean.Options;

public interface IOptionsService {
    
	Page<Options> findAll() throws Exception;
	Options findById(long id) throws Exception;
	void saveOrUpdate(Options options) throws Exception;
	void deleteById(long id) throws Exception;

}
