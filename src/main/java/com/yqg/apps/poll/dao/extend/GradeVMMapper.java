package com.yqg.apps.poll.dao.extend;

import java.util.List;

import com.github.pagehelper.Page;
import com.yqg.apps.poll.bean.extend.GradeVM;
import org.apache.ibatis.annotations.*;


public interface GradeVMMapper {
	@Select("SELECT * FROM poll_grade")
	@Results({
			@Result(property = "school",column = "school_id",
			one = @One(select = "com.yqg.apps.poll.dao.SchoolMapper.selectById"))
	})
	Page<GradeVM> SelectAll(Integer pageNo,Integer pageSize);

	@Select("SELECT * FROM poll_grade WHERE id = #{id}")
	@Results({
			@Result(property = "school",column = "school_id",
					one = @One(select = "com.yqg.apps.poll.dao.SchoolMapper.selectById"))
	})
	GradeVM selectByPrimaryKey(long id);

	@Select("SELECT * FROM poll_grade WHERE description like CONCAT('%', #{text},'%')")
	@Results({
			@Result(property = "school",column = "school_id",
					one = @One(select = "com.yqg.apps.poll.dao.SchoolMapper.selectById"))
	})
	List<GradeVM> selectByExampleWithBLOBs(String text);
	
}
