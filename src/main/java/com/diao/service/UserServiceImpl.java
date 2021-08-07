package com.diao.service;

import com.diao.dao.UserMapper;
import com.diao.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private UserMapper userMapper;
    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User getUserById(String username) {
        return userMapper.getUserById(username);
    }
}
