package com.yqg.apps.poll.web.controller;



import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yqg.apps.poll.bean.Options;
import com.yqg.apps.poll.service.IOptionsService;
import com.yqg.apps.poll.util.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

@Api(description="问题选项模块接口")
@RestController
@RequestMapping("/options")
public class OptionsController {
	@Autowired
	private IOptionsService optionsService;

	@PostMapping("saveOrUpdate")
	public MsgResponse save(Options options){
		try{
			optionsService.saveOrUpdate(options);	
			return MsgResponse.success("success",null);
			 
		}catch(Exception e){
			e.printStackTrace();
			return MsgResponse.error("操作失败");
		}
	}
	
	@GetMapping("deleteById")
	public MsgResponse deleteById(long id){
		try{
			optionsService.deleteById(id);
			return MsgResponse.success("success",null);
			 
		}catch(Exception e){
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@GetMapping("findById")
	public MsgResponse findById(@RequestParam long id){
		try{
			Options options=optionsService.findById(id);
			if(options != null){
				return MsgResponse.success("success", options);
			}else {
				return MsgResponse.error("查询对象不存在");
			}
			}catch(Exception e){
				e.printStackTrace();
				return MsgResponse.error(e.getMessage());
			}	
		}
	

	@GetMapping("findAllOptions")
	public MsgResponse findAllQuestion(@RequestParam(defaultValue = "1") Integer pageNo,@RequestParam(defaultValue = "5") Integer pageSize){
		try{
			PageHelper.startPage(pageNo,pageSize);
			 Page<Options> list=optionsService.findAll();
			 return MsgResponse.success("success", list);
			}catch(Exception e){
				e.printStackTrace();
				return MsgResponse.error(e.getMessage());
			}	
		}
}
