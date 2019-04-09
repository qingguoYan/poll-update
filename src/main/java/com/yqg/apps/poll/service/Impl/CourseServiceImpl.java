package com.yqg.apps.poll.service.Impl;

import java.util.List;

import com.github.pagehelper.Page;
import com.yqg.apps.poll.dao.CourseMapper;
import com.yqg.apps.poll.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yqg.apps.poll.bean.Course;

@Service
public class CourseServiceImpl implements ICourseService {
	@Autowired
	private CourseMapper courseMapper;


	@Override
	public Page<Course> findAll() throws Exception {
		return courseMapper.findAll();
	}

	@Override
	public Course findById(long id) throws Exception {
		return courseMapper.findById(id);
	}



	@Override
	public void deleteById(long id) throws Exception {
             courseMapper.deleteById(id);
	}



	@Override
	public void saveOrUpdate(Course course) throws Exception {
               if(course.getId() == null){
               	courseMapper.insert(course);
			   }else {
               	courseMapper.update(course);
			   }
	}
}
