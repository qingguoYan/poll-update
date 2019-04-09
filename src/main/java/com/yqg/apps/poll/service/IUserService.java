package com.yqg.apps.poll.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.yqg.apps.poll.bean.User;
import com.yqg.apps.poll.bean.extend.UserVM;

public interface IUserService  {
	/*
	 * 查询所有数据
	 */

	Page<User> findAll() throws Exception;
	
	Page<UserVM> findAllVM() throws Exception;
	/*
	 * 通过ID查询一行数据
	 */
	User findById(long id) throws Exception;

	/*
	 * 通过删除一行数据
	 */
	void deleteById(long id) throws Exception;
	/*
	 * 保存和更新数据
	 */
	void saveOrUpdate(User user) throws Exception;
	
	void save(UserVM userVM) throws Exception;

	
}
