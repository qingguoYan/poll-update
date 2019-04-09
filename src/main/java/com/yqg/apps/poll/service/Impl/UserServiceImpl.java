package com.yqg.apps.poll.service.Impl;

import java.util.List;

import com.github.pagehelper.Page;
import com.yqg.apps.poll.dao.extend.UserVMMapper;
import com.yqg.apps.poll.service.IUserService;
import org.springframework.stereotype.Service;

import com.yqg.apps.poll.bean.Clazz;
import com.yqg.apps.poll.bean.User;
import com.yqg.apps.poll.bean.extend.UserVM;
import com.yqg.apps.poll.dao.ClazzMapper;
import com.yqg.apps.poll.dao.UserMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {
	@Resource
	private UserMapper userMapper;
	@Resource
	private UserVMMapper userVMMapper;

	@Resource
	private ClazzMapper clazzMapper;
	
///查找全部
	@Override
	public Page<User> findAll() throws Exception {
		return userMapper.selectAll();
	
	}
	
	public Page<UserVM> findAllVM() throws Exception {
		return userVMMapper.selectAll();
	}

	@Override
	public User findById(long id) throws Exception {
		return userMapper.findById(id);
	}


	//通过ID删除
	@Override
	public void deleteById(long id) throws Exception {
		userMapper.deleteByPrimaryKey(id);
		
	}
	

	
///添加和保存
	@Override
	public void saveOrUpdate(User user) throws Exception {
		//ID不为空 更新一条记录
		if(user.getId()!=null){
			userMapper.update(user);
		}
	    else{
		//ID为空 添加一条记录
		userMapper.insert(user);
	}
	}
	
	@Override
	@Transactional
	public void save(UserVM userVM) throws Exception {
			User user = new User();
			user.setBirth(userVM.getBirth());
			user.setGender(userVM.getGender());
			user.setName(userVM.getName());
			user.setHiredate(userVM.getHiredate());
			user.setType(userVM.getType());
			userMapper.insert(user);
			List<Clazz> list = userVM.getClazz();
			for(Clazz clazz:list){
				System.out.println(user.getId());
				clazz.setChargeId(user.getId());
				clazz.setGradeId(userVM.getGradeId());
				clazzMapper.insert(clazz);
			}
		
		
	}

	
///批量删除
	public void batchDelete(List<Long> ids) throws Exception{
		for(long id:ids){
			userMapper.deleteByPrimaryKey(id);
			
		}
		
	}




}
