package com.yqg.apps.poll.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.yqg.apps.poll.bean.Course;

public interface ICourseService {
  Page<Course> findAll() throws Exception;
  Course findById(long id) throws Exception;//id搜索

  void deleteById(long id) throws Exception;

  void saveOrUpdate(Course course) throws Exception;//保存或者
}
