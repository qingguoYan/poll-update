package com.yqg.apps.poll.web.controller;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yqg.apps.poll.bean.Grade;
import com.yqg.apps.poll.bean.extend.GradeVM;
import com.yqg.apps.poll.service.IGradeService;
import com.yqg.apps.poll.util.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="年级相关接口")
@RestController
@RequestMapping("/grade")
public class GradeController {
	@Autowired
	private IGradeService gradeService;

	@ApiOperation(value="查询年级所有信息",notes="查询结果不包含School")
	@GetMapping("findAllGrade")
	public MsgResponse findAllGrade(@RequestParam(defaultValue = "1")Integer pageNo,@RequestParam(defaultValue = "5") Integer pageSize){
		try{
			PageHelper.startPage(pageNo,pageSize);
			Page<Grade> list=gradeService.findAll();
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

	@ApiOperation(value="查询班级所有信息",notes="查询结果包含School")
	@GetMapping("findAllGradeVM")
	public MsgResponse findAllGradeVM(@RequestParam(defaultValue = "1")Integer pageNo,@RequestParam(defaultValue = "5") Integer pageSize){
		try {
			PageHelper.startPage(pageNo,pageSize);
			Page<GradeVM> list=gradeService.findAllGradeVM(pageNo,pageSize);
			return MsgResponse.success("success", list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}

	}


	@ApiOperation(value="通过description查询年级信息",notes="查询结果包含school")
	@GetMapping("findKeyWords")
	public MsgResponse findKeyWords(String keywords){
		try {
			List<GradeVM> list=gradeService.query(keywords);
			return MsgResponse.success("find key words:"+keywords,list);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

	@ApiOperation(value="增加或修改年级信息",notes="输入ID为修改信息，不输入ID是增加信息")
	@PostMapping("saveOrUpdateGrade")
	public MsgResponse saveOrUpdateGrade(Grade grade){
		try {
			gradeService.saveOrUpdate(grade);
			return MsgResponse.success("success",grade);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

	@ApiOperation(value="通过ID删除年级信息")
	@GetMapping("deleteById")
	public MsgResponse deleteByIdGrade(@RequestParam long id){
		try {
			GradeVM gradeVM = gradeService.findById(id);
			if(gradeVM != null){
				gradeService.deleteById(id);
				return MsgResponse.success("success",id);
			}else {
				return MsgResponse.error("该对象不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}


	@ApiOperation(value="查询",notes="输入班级id")
	@GetMapping("findById")
	//多对一查询,传入Grade的id
	public MsgResponse findWithSchoolById(int id){
		try{
			GradeVM gradeVM = gradeService.findWithSchoolById(id);
			if(gradeVM != null){
				return MsgResponse.success("success",gradeVM);
			}else {
				return MsgResponse.error("查询对象不存在");
			}

		}catch (Exception e){
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}


//	@ApiOperation(value = "批量查找",notes="输入班级ids")
//	@GetMapping("batchFind")
//	public MsgResponse batchFind(@RequestParam List<Long> ids){
//		try{
//			List<Grade> list = gradeService.batchFind(ids);
//			return MsgResponse.success("success",list);
//		}catch (Exception e){
//			e.printStackTrace();
//			return MsgResponse.error(e.getMessage());
//		}
//	}

}
