package com.yqg.apps.poll.service.Impl;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.Page;
import com.yqg.apps.poll.dao.extend.GradeVMMapper;
import org.springframework.stereotype.Service;

import com.yqg.apps.poll.bean.Grade;
import com.yqg.apps.poll.bean.extend.GradeVM;
import com.yqg.apps.poll.dao.GradeMapper;
import com.yqg.apps.poll.service.IGradeService;

import javax.annotation.Resource;

@Service
public class GradeServiceImpl implements IGradeService{
	@Resource
	private GradeMapper gradeMapper;
	
	@Resource
	private GradeVMMapper gradeVMMapper;


	@Override
	public Page<Grade> findAll() throws Exception {
		return gradeMapper.findAll();
	}

	@Override
	public GradeVM findById(long id) throws Exception {
		return gradeVMMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<GradeVM> query(String keywords) throws Exception {
		return gradeVMMapper.selectByExampleWithBLOBs(keywords);
	}

	@Override
	public void saveOrUpdate(Grade grade) throws Exception {

		if (grade.getId()!=null) {
			gradeMapper.update(grade);
			}else {
				gradeMapper.insert(grade);
			}
	}

	@Override
	public void deleteById(long id) throws Exception {
		gradeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(List<Long> ids) throws Exception {
		for(long id:ids){
			gradeMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public List<Grade> findBySchoolId(int id) {
		return gradeMapper.findBySchoolId(id);
	}

	@Override
	public GradeVM findWithSchoolById(int id) throws Exception {
		return gradeMapper.findWithSchoolById(id);
	}

	@Override
	public List<Grade> batchFind(List<Long> ids) {
		ArrayList<Grade> grades = new ArrayList<>();
		for(Long id :ids){
			Grade grade = gradeMapper.findById(id);
			grades.add(grade);
		}
		return grades;
	}

	@Override
	public Page<GradeVM> findAllGradeVM(Integer pageNo,Integer pageSize) throws Exception {
		return gradeVMMapper.SelectAll(pageNo,pageSize);
	}

}
