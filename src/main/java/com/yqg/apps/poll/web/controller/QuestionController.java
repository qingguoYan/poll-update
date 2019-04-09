package com.yqg.apps.poll.web.controller;



import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yqg.apps.poll.bean.extend.QuestionVM;
import com.yqg.apps.poll.service.IQuestionService;
import com.yqg.apps.poll.util.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="问题模块接口")
@RestController
@RequestMapping("/question")
public class QuestionController {
	@Autowired
	private IQuestionService questionService;
	
	@ApiOperation(value="删除问题",notes="同时级联删除options")
	@GetMapping("deleteQuestionById")
	public MsgResponse deleteQuestionQuestionById(long id){
		try{
			questionService.deleteById(id);
			return MsgResponse.success("删除成功", null);
		}catch(Exception e){
			return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="保存或者更新问题",notes="如果id为空，执行保存操作，如果id不为空，执行更新操作")
	@PostMapping("saveOrUpdateQuestion")
	public MsgResponse saveOrUpdateQuestion(@RequestBody QuestionVM questionVM){
         try {
			questionService.saveOrUpdateQuestionVM(questionVM);
			return MsgResponse.success("success", null);
		} catch (Exception e) {
			
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
			
		}
	
	}
	
	@ApiOperation(value="查找所有问题",notes="并查找出所有问题的选项")
	@GetMapping("findAllQuestion")
	public MsgResponse findAllQuestion(@RequestParam(defaultValue = "1") Integer pageNo,@RequestParam(defaultValue = "5") Integer pageSize){
		try{
			PageHelper.startPage(pageNo,pageSize);
			 Page<QuestionVM> list=questionService.findAll();
			 return MsgResponse.success("success", list);
			}catch(Exception e){
				e.printStackTrace();
				return MsgResponse.error(e.getMessage());
			}	
		}


	}

