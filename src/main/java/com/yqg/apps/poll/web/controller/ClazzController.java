package com.yqg.apps.poll.web.controller;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yqg.apps.poll.bean.Clazz;
import com.yqg.apps.poll.bean.extend.ClazzVM;
import com.yqg.apps.poll.service.IClazzService;
import com.yqg.apps.poll.util.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="班级相关接口")
@RestController
@RequestMapping("/clazz")
public class ClazzController {
	@Autowired
	private IClazzService clazzService;
	@ApiOperation(value="查询班级所有信息",notes="查询结果不包含Grade,User")
	@GetMapping("findAllClazz")
	public MsgResponse findAllClazz(@RequestParam(defaultValue = "1") Integer pageNo,@RequestParam(defaultValue = "5") Integer pageSize){
		try {
			PageHelper.startPage(pageNo,pageSize);
		    Page<Clazz> list=clazzService.findAll();
		    return MsgResponse.success("success", list);
		}
		catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="查询班级所有信息",notes="查询结果包括Grade,User")
	@GetMapping("findAllClazzVM")
	public MsgResponse findAllClazzVM(@RequestParam(defaultValue = "1") Integer pageNo,@RequestParam(defaultValue = "5") Integer pageSize){
		try {
			PageHelper.startPage(pageNo,pageSize);
		    Page<ClazzVM> list=clazzService.findAllClazzVM();
		return MsgResponse.success("success", list);
		}
		catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	@ApiOperation(value="通过ID查询班级信息",notes="查询结果包含Grade,User")
	@GetMapping("findByIdClazzVM")
	public MsgResponse findByIdClazzVM(@RequestParam long id){
		try {
			ClazzVM clazzVM = clazzService.selectById(id);
			if(clazzVM != null){
				return MsgResponse.success("ID："+id+"find success",clazzVM);
			}else {
				return MsgResponse.error("查询对象不存在");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}	
	}



	@ApiOperation(value="增加或修改班级信息",notes="输入ID为修改信息，不输入ID是增加信息")
	@PostMapping("saveorUpdateClazz")
	public MsgResponse saveorUpdateClazz(Clazz clazz){
		try {
			clazzService.saveOrUpdate(clazz);;
			return MsgResponse.success("更改数据成功",clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	

	@ApiOperation(value="通过ID删除信息")
	@GetMapping("deleteByIdClazz")
	public MsgResponse deleteByIdClazz(@RequestParam long id){
		try {
			clazzService.deleteById(id);
			return MsgResponse.success("DELETE：",id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
}
