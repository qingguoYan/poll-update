package com.yqg.apps.poll.web.controller;



import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yqg.apps.poll.bean.User;
import com.yqg.apps.poll.bean.extend.UserVM;
import com.yqg.apps.poll.service.IUserService;
import com.yqg.apps.poll.util.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(description="辅导员相关接口")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired	
	private IUserService userService;

	@GetMapping("findAllUser") 
	public MsgResponse findAllUser(@RequestParam(defaultValue = "1")Integer pageNo, @RequestParam(defaultValue = "5")Integer pageSize){
		try{
			PageHelper.startPage(pageNo,pageSize);
			Page<User> list =userService.findAll();
			return MsgResponse.success("success",list);
		}catch(Exception e){
			e.printStackTrace();
	        return MsgResponse.error(e.getMessage());
		}
	}
	
	@ApiOperation(value="查询所有辅导员信息",notes="辅导员用户信息中包含的班级信息")
	@GetMapping("findAllUserVM") 
	public MsgResponse findAllUserVM(@RequestParam(defaultValue = "1")Integer pageNo, @RequestParam(defaultValue = "5")Integer pageSize){
		try{
			PageHelper.startPage(pageNo,pageSize);
			Page<UserVM> list =userService.findAllVM();
			return MsgResponse.success("success",list);
		}catch(Exception e){
			e.printStackTrace();
	        return MsgResponse.error(e.getMessage());
		}
	}

	

	@GetMapping("findById")
    public MsgResponse findByIdUser(long id){
    	try{
			User user =userService.findById(id);
			if (user != null){
				return MsgResponse.success("success",user);
			}else {
				return MsgResponse.error("查询对象不存在");
			}
		}catch(Exception e){
			e.printStackTrace();
	        return MsgResponse.error(e.getMessage());
    }
    }



	@GetMapping("deleteByIdUser") 
    public MsgResponse deleteByIdUser(long id){
        		
        try{
        	userService.deleteById(id);
		    return MsgResponse.success("success",id);
        }catch(Exception e){
        	e.printStackTrace();
        	return MsgResponse.error(e.getMessage());
    }	
	}
	

	@PostMapping("saveOrUpdateUser")
	 public MsgResponse saveOrUpdateUser(User user){
	    	try{
	    		userService.saveOrUpdate(user);
	    		return MsgResponse.success("success",user);
	    	}catch(Exception e){
	        	e.printStackTrace();
	        	return MsgResponse.error(e.getMessage());
	    }		
	    }
	
	@PostMapping("saveUserVM")
	 public MsgResponse saveUserVM(@RequestBody UserVM userVM){
	    	try{
	    		userService.save(userVM);
	    		return MsgResponse.success("success",userVM);
	    	}catch(Exception e){
	        	e.printStackTrace();
	        	return MsgResponse.error(e.getMessage());
	    }		
	    }

   

}
