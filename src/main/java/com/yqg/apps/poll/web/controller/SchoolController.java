package com.yqg.apps.poll.web.controller;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yqg.apps.poll.bean.extend.SchoolVM;
import com.yqg.apps.poll.bean.School;
import com.yqg.apps.poll.service.ISchoolService;
import com.yqg.apps.poll.util.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(description="学校相关接口")
@RestController
@RequestMapping("/school")
public class SchoolController {
@Autowired
private ISchoolService schoolService;
@ApiOperation(value="查询学校信息",notes="单表")
@GetMapping("findAllSchool")
public MsgResponse findAllSchool(Integer pageNo,Integer pageSize){
try{
	if(pageNo == null){
		pageNo = 1;
	}
	if(pageSize == null){
		pageSize = 5;
	}
	PageHelper.startPage(pageNo,pageSize);
	Page<School> list=schoolService.findAll();
	PageInfo pageInfo = new PageInfo(list);
	System.out.println(list.getTotal());
	//返回成功信息
	return MsgResponse.success("success", list);
}catch (Exception e){
	e.printStackTrace();
	//返回失败信息
	return MsgResponse.error(e.getMessage());
}
}
@ApiOperation(value="通过关键字查询")
@GetMapping("findAllKeywords")
/*
 * 通过关键字模糊查询
 */
public MsgResponse findAllKeywords(String keywords)
{
	try
	{
		return MsgResponse.success("success",schoolService.query(keywords));
	}catch(Exception e)
	{
		e.printStackTrace();
		return MsgResponse.error(e.getMessage());
	}
}
@ApiOperation(value="Id搜索学校信息")
@GetMapping("findById")
public MsgResponse findSchoolById(@RequestParam long id){
		try {
			School school= schoolService.findById(id);
			//返回成功信息
			if(school != null){
				return MsgResponse.success("success",school );
			}else {
				return MsgResponse.error("对象不存在" );
			}
		} catch (Exception e) {
			e.printStackTrace();
			//返回失败信息
			return MsgResponse.error(e.getMessage());
		}
	}
@ApiOperation(value="保存或修改学校信息")
@PostMapping("saveOrupdateSchool")
public MsgResponse saveOrupdateSchool(School school){
	  //调用service层代码完成课程信息的删除
	  try {
		schoolService.saveOrupdate(school);
		return MsgResponse.success("success", "成功");
	  }catch (Exception e){
	  	e.printStackTrace();
	  	//返回失败信息
	  	return MsgResponse.error(e.getMessage());
	  }
}
@ApiOperation(value="通过ID删除学校信息")
@GetMapping("deleteSchoolById")
public MsgResponse deleteSchoolById(@RequestParam long id){
	  //调用service层代码完成课程信息的删除
	  try {
		schoolService.deleteById(id);
		return MsgResponse.success("success", "删除成功");
	  }catch (Exception e){
	  	e.printStackTrace();
	  	//返回失败信息
	  	return MsgResponse.error(e.getMessage());
	  }

}
@ApiOperation(value="批量删除学校信息")
@GetMapping("batchDelete")
public MsgResponse batchDelete(@RequestParam List<Long> ids){
	  try {
		 schoolService.batchDelete(ids);
		return MsgResponse.success("success","删除成功");
	} catch (Exception e) {
		e.printStackTrace();
		return MsgResponse.error(e.getMessage());
	}
}

@GetMapping("findWithGrade")
//一对多查询
public MsgResponse findWithGrade(@RequestParam int id){
	try{
		SchoolVM school = schoolService.findWithGrade(id);
		return MsgResponse.success("success",school);
	}catch (Exception e){
		e.printStackTrace();
		return MsgResponse.error(e.getMessage());
	}
}

}