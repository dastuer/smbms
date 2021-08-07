package com.diao.dao;

import com.diao.pojo.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from ssmbuild.user where username=#{username}")
    User getUserById(String username);
}
