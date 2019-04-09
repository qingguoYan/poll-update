package com.yqg.apps.poll.service.Impl;

import java.util.List;

import com.github.pagehelper.Page;
import com.yqg.apps.poll.service.IClazzService;
import org.springframework.stereotype.Service;

import com.yqg.apps.poll.bean.Clazz;
import com.yqg.apps.poll.bean.extend.ClazzVM;
import com.yqg.apps.poll.dao.ClazzMapper;
import com.yqg.apps.poll.dao.extend.ClazzVMMapper;

import javax.annotation.Resource;

@Service
public class ClazzServiceImpl implements IClazzService {
	@Resource
	private ClazzMapper clazzMapper;
	
	@Resource
	private ClazzVMMapper clazzVMMapper;
	

	@Override
	public Page<Clazz> findAll() throws Exception {
		return clazzMapper.selectAll();
	}

	@Override
	public ClazzVM selectById(long id) throws Exception {
		return clazzVMMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ClazzVM> query(String keywords) throws Exception {
		return clazzVMMapper.selectByExampleWithBLOBs(keywords);
	}

	@Override
	public void saveOrUpdate(Clazz clazz) throws Exception {

		if (clazz.getId()!=null) {
			clazzMapper.update(clazz);
			}else {
				clazzMapper.insert(clazz);
			}
	}

	@Override
	public void deleteById(long id) throws Exception {
		clazzMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(List<Long> ids) throws Exception {
		for(long id:ids){
			clazzMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public Page<ClazzVM> findAllClazzVM() throws Exception {
		return clazzVMMapper.SelectAll();
	}
}
