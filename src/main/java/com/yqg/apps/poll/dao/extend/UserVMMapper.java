package com.yqg.apps.poll.dao.extend;

import java.util.List;


import com.github.pagehelper.Page;
import com.yqg.apps.poll.bean.extend.UserVM;
import org.apache.ibatis.annotations.*;


public interface UserVMMapper {
      @Select("SELECT * FROM poll_user")
      @Results({
              @Result(id = true,column = "id",property = "id"),
              @Result(property = "clazz",column = "id",
              many = @Many(select = "com.yqg.apps.poll.dao.ClazzMapper.findById"))
      })
      Page<UserVM> selectAll();

      void insert(UserVM userVM);
      void deleteByPrimaryKeys(long id);
}
