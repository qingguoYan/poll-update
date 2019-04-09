package com.yqg.apps.poll.dao.extend;

import java.util.List;

import com.github.pagehelper.Page;
import com.yqg.apps.poll.bean.extend.ClazzVM;
import org.apache.ibatis.annotations.*;


public interface ClazzVMMapper {

	@Select("SELECT * FROM poll_clazz")
	@Results({
			@Result(id = true,property = "id",column = "id"),
			@Result(property = "grade",column = "grade_id",
			one = @One(select = "com.yqg.apps.poll.dao.GradeMapper.findById")),
			@Result(property = "charge",column = "charge_id",
			one = @One(select = "com.yqg.apps.poll.dao.UserMapper.findById"))
	})
	Page<ClazzVM> SelectAll();


    @Select("SELECT * FROM poll_clazz WHERE id = #{id}")
	@Results({
			@Result(id = true,property = "id",column = "id"),
			@Result(property = "grade",column = "grade_id",
					one = @One(select = "com.yqg.apps.poll.dao.GradeMapper.findById")),
			@Result(property = "charge",column = "charge_id",
					one = @One(select = "com.yqg.apps.poll.dao.UserMapper.findById"))
	})
	ClazzVM selectByPrimaryKey(long id);


	
   @Select("SELECT * FROM poll_clazz WHERE description like CONCAT('%', #{keywords} ,'%')")
	List<ClazzVM> selectByExampleWithBLOBs(String keywords);


    

}
