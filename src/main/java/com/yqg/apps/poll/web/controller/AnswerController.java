package com.yqg.apps.poll.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yqg.apps.poll.bean.Answers;
import com.yqg.apps.poll.bean.extend.AnswersVM;
import com.yqg.apps.poll.service.IAnswersService;
import com.yqg.apps.poll.util.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description="答案相关接口")
@RestController
@RequestMapping("/answers")
public class AnswerController {

	@Autowired
	private IAnswersService answersService;


	@ApiOperation(value="查询所有答案",notes="一键查所有答案携带课调信息")
	@GetMapping("findAllAnswersVM")
	public MsgResponse findAllAnswersVM(@RequestParam(defaultValue = "1")Integer pageNo,@RequestParam(defaultValue = "5") Integer pageSize){
		try{
			PageHelper.startPage(pageNo,pageSize);
		    Page<AnswersVM> list=answersService.findAllAnswersVM();
		 return MsgResponse.success("success", list);
		}catch(Exception e){
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
		
	}

	@ApiOperation(value="通过id删除答案信息",notes="通过id删除")
	@GetMapping("deleteById")
	public MsgResponse deleteById(long id) {
		try {
			answersService.deleteById(id);
			return MsgResponse.success("success", id);
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

	@ApiOperation(value="通过id查询答案信息",notes="通过id查询")
	@GetMapping("findById")
	public MsgResponse findById(long id) {
		try {
			Answers answers = answersService.findById(id);
			if(answers != null){
				return MsgResponse.success("success", answers);
			}else {
				return MsgResponse.error("对象不存在");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

}
