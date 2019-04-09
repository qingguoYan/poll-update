package com.yqg.apps.poll.web.controller;


import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yqg.apps.poll.bean.Answers;
import com.yqg.apps.poll.bean.Survey;
import com.yqg.apps.poll.bean.extend.AnswersVM;
import com.yqg.apps.poll.bean.extend.SurveyVM;
import com.yqg.apps.poll.service.IAnswersService;
import com.yqg.apps.poll.service.ISurveyService;
import com.yqg.apps.poll.util.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(description="课程调查相关接口")
@RestController
@RequestMapping("/Survey")
public class SurveyController {

	@Autowired
	private ISurveyService surveyService;
	@Autowired
	private IAnswersService answersService;
	
	@ApiOperation(value="开启课调",notes="只有在课调状态为未开启的时候才能开启课调")
	@GetMapping("beginSurvey")
	public MsgResponse beginSurvey(long id){
		try{
			//1.通过id查询课调
			Survey survey=surveyService.findSurveyById(id);
	        //2.修改课调状态、课程编号
			if(survey.getStatus().equals(Survey.STATUS_INIT)){
				//2.1当前课调状态设置为开启
				survey.setStatus(Survey.STATUS_BEGIN);
//				//2.2将课调的code设置为当前课调的id，后期要通过code找survey
				survey.setCode(survey.getId().toString());
				//2.3执行或保存更新操作
				surveyService.saveOrUpdate(survey);
				return MsgResponse.success("开启成功",null);
			}else{
				return MsgResponse.error("当前课调状态必须为未开启状态");
			}
		}catch(Exception e){
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}

	}

	@ApiOperation(value="关闭课调",notes="只有在课调状态为开启的时候才能开启课调")
	@GetMapping("closeSurvey")
	public MsgResponse closeSurvey(long id){
		try{
			//1.通过id查询课调
			Survey survey=surveyService.findSurveyById(id);
			//2.修改课调状态、课程编号
			if(survey.getStatus().equals(Survey.STATUS_BEGIN)){
				//2.1当前课调状态设置为开启
				survey.setStatus(Survey.STATUS_CHECK_UN);
				//2.3执行或保存更新操作
				surveyService.saveOrUpdate(survey);
				return MsgResponse.success("关闭成功",null);
			}else{
				return MsgResponse.error("当前课调状态必须为开启状态");
			}
		}catch(Exception e){
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

	@ApiOperation(value="去审核课调",notes="查询课调下所有的问卷信息")
	@GetMapping("toCheckSurvey")
	public MsgResponse toCheckSurvey(long id){
		try{
			//1.通过id查询课调
			SurveyVM surveyVM  = surveyService.findByIdSurveyVM(id);
			if(surveyVM != null){
			if(surveyVM.getStatus().equals(Survey.STATUS_CHECK_UN)){
			    List<Answers> list = answersService.findAllAnswers();
				double total = 0;
			    for(Answers answers :list){
			    	String[] str = answers.getSelections().split("[|]");
			    	double singleTotal = 0;
			    	for(String a : str){
			    		singleTotal += Integer.parseInt(a);
			    	}
			    	double singleAverage = singleTotal/str.length;
			    	total += singleAverage;
				}
			    double average = total/list.size();
			    Survey survey = surveyService.findSurveyById(id);
			    survey.setAverage(average);
			    surveyService.saveOrUpdate(survey);
				return MsgResponse.success("success",surveyVM);
			}else{
				return MsgResponse.error("当前课调状态必须为关闭状态");
			}
			}else {
				return MsgResponse.error("查询对象不存在");
			}
		}catch(Exception e){
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

	@ApiOperation(value="审核课调",notes="id为课调编号,status代表审核是否通过,0不通过,1通过")
	@GetMapping("checkSurvey")
	public  MsgResponse checkSurvey(long id,int status){
         try{
         	Survey survey = surveyService.findSurveyById(id);
         	if(survey != null && survey.getStatus().equals(Survey.STATUS_CHECK_UN)){
         		if(status == 0){
         			survey.setStatus(Survey.STATUS_CHECK_NOPASS);
				}else if(status == 1){
         			survey.setStatus(Survey.STATUS_CHECK_PASS);
				}
				surveyService.saveOrUpdate(survey);
         		return MsgResponse.success("审核完成",null);
			}else {
         		return MsgResponse.error("课调状态不合法");
			}
		 }catch (Exception e){
         	e.printStackTrace();
         	return  MsgResponse.error(e.getMessage());
		 }
	}

	@ApiOperation(value="预览课调",notes="id为课调编号,只有课调状态为已通过的时候才能预览")
	@GetMapping("viewSurvey")
	public MsgResponse viewSurvey(long id){
          try{
          	SurveyVM surveyVM = surveyService.findByIdSurveyVM(id);
          	if(surveyVM != null && surveyVM.getStatus().equals(Survey.STATUS_CHECK_PASS)){

				return MsgResponse.success("success",surveyVM);
			}else {
          		return MsgResponse.error("课调状态不合法");
			}
		  }catch (Exception e){
          	e.printStackTrace();
          	return MsgResponse.error(e.getMessage());
		  }
	}

	@ApiOperation(value="查询所有课调",notes="课程调查中包含班级等信息")
	@GetMapping("findAllSurveyVM")
	public MsgResponse findAllSurveyVM(@RequestParam(defaultValue = "1") Integer pageNo,@RequestParam(defaultValue = "5") Integer pageSize){
		try{
			PageHelper.startPage(pageNo,pageSize);
			Page<SurveyVM> list=surveyService.findAll();
			return MsgResponse.success("success",list);
		}catch(Exception e){
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

	@ApiOperation(value = "通过id查找课调,级联查询课调其他信息",notes = "请输入课调id")
	@GetMapping("findSurveyVMById")
	public MsgResponse findSurveyVMById(@RequestParam long id){
		try{
			SurveyVM surveyVM = surveyService.findByIdSurveyVM(id);
			if (surveyVM != null){
				return MsgResponse.success("success",surveyVM);
			}else {
				return MsgResponse.error("查询对象不存在");
			}

		}catch(Exception e){
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

	
    @ApiOperation(value="保存或更新课调",notes="如果参数中包含id表示修改，否则添加，只需接受clazzId，courseId,userId,questionnaireId")
	@PostMapping("saveOrUpdateSurvey")
	public MsgResponse saveOrUpdateSurvey(Survey survey){
		try{
			surveyService.saveOrUpdate(survey);
			return MsgResponse.success("保存或更新成功",survey);
		}catch(Exception e){
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}
	
    @ApiOperation(value="通过ID删除课调",notes="级联课调下的答案信息")
	@GetMapping("deleteSurveyById")
	public MsgResponse deleteSurveyById(@RequestParam long id){
		try{
			surveyService.deleteById(id);
			return MsgResponse.success("删除成功",null);
		}catch(Exception e){
			e.printStackTrace();
			return MsgResponse.error(e.getMessage());
		}
	}

}
