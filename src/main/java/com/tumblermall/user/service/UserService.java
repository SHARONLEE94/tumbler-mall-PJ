package com.tumblermall.user.service;

import com.tumblermall.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public String userNameTest(String userId) {
        String id = userMapper.selectUserId(userId);
        return id;

//        return userMapper.selectUserId(userId);
    }

}