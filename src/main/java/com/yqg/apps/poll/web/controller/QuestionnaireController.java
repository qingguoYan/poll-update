package com.yqg.apps.poll.web.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yqg.apps.poll.bean.Questionnaire;
import com.yqg.apps.poll.bean.extend.QuestionnaireVM;
import com.yqg.apps.poll.service.IQuestionnaireService;
import com.yqg.apps.poll.util.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(description="问卷相关接口")
@RestController
@RequestMapping("/Questionnaire")
public class QuestionnaireController {
	/**
	 * 三个问题: 1.多对多查询
	 *          2.多对多级联保存
	 *          3.级联删除策略
	 */
	@Autowired
	private IQuestionnaireService questionnaireService;

	@ApiOperation(value="查询所有问卷调查信息",notes="单表查询所有问卷")
	@GetMapping("findAllQuestionaire")
	public MsgResponse findAllAnswers(@RequestParam(defaultValue = "1")Integer pageNo, @RequestParam(defaultValue = "5") Integer pageSize){
		try{
			PageHelper.startPage(pageNo,pageSize);
		    Page<Questionnaire> list=questionnaireService.findAll();
		 return MsgResponse.success("success", list);
		}catch(Exception e){
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
		
	}
	
	@ApiOperation(value="级联查询题目，题目不带有选项",notes="请输入问卷id")
	@GetMapping("findById")
	public MsgResponse findById(long id){
		try {
			Questionnaire questionnaire = questionnaireService.findById(id);
			if(questionnaire != null){
				return MsgResponse.success("success", questionnaire);
			}else {
				return MsgResponse.error("查询对象不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

	@ApiOperation(value="级联查询题目，题目带有选项",notes="请输入问卷id")
	@GetMapping("findByIdVM")
	public MsgResponse findByIdVM(long id){
		try{
			QuestionnaireVM questionnaire = questionnaireService.findByIdVM(id);
			if(questionnaire != null){
				return MsgResponse.success("succes",questionnaire);
			}else {
				return MsgResponse.error("查询对象不存在");
			}
		}catch (Exception e){
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

//	@ApiOperation(value="更新问卷信息",notes="单表操作,只更新问卷信息")
//	@PostMapping("updateQuestionnaire")
//	public MsgResponse updateQuestionnaire(@RequestBody Questionnaire questionnaire) {
//		try {
//			questionnaireService.update(questionnaire);
//			return MsgResponse.success("success", questionnaire);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return MsgResponse.error(e.getMessage());
//		}
//	}

	@ApiOperation(value = "保存问卷，同时级联保存问卷以及题目和题目选项",notes = "请输入json数据")
    @PostMapping("saveQuestionnaire")
	public MsgResponse saveQuestionnaire(@RequestBody QuestionnaireVM questionnaireVM){
		try{
			questionnaireService.save(questionnaireVM);
			return MsgResponse.success("success",questionnaireVM);
		}catch (Exception e){
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

}