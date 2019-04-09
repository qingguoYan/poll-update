package com.yqg.apps.poll.web.controller;



import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yqg.apps.poll.bean.Course;
import com.yqg.apps.poll.service.ICourseService;
import com.yqg.apps.poll.util.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(description="课程相关的接口")
@RestController
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private ICourseService courseService;
	
	@ApiOperation(value="查询所有课程信息",notes="单表")
	@GetMapping("findAllCourse")//参数和方法类保持一致
	public MsgResponse findAllCourse(@RequestParam(defaultValue = "1") Integer pageNo,@RequestParam(defaultValue = "5")  Integer pageSize){
		try {
			PageHelper.startPage(pageNo,pageSize);
			Page<Course> list = courseService.findAll();
			return MsgResponse.success("success",list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="通过id查询课程信息")
	@GetMapping("findByIdCourse")
	public  MsgResponse findCourseByIdl(@RequestParam long id){
		try {
			Course course=courseService.findById(id);
			if(course != null){
				return MsgResponse.success("success",course);
			}else {
				return MsgResponse.error("查询对象不存在");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

	
	@ApiOperation(value="保存和更新课程信息")
	@PostMapping("saveOrUpdateCourse")
	public  MsgResponse saveOrUpdate(Course course){
		try {
		     courseService.saveOrUpdate(course);
			return MsgResponse.success("success",course);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="单个删除课程信息")
	  @GetMapping("deleteCourseById")
	  public MsgResponse deleteCourseById(@RequestParam long id){
		  try {
			courseService.deleteById(id);
			return MsgResponse.success("success","删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		} 
	  }

	}


