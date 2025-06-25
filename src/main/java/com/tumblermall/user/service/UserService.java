package com.tumblermall.user.service;

import com.tumblermall.user.dto.UserInfoResponseDTO;
import com.tumblermall.user.mapper.UserMapper;
import com.tumblermall.user.vo.UserInfoResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public String userNameTest(String userId) throws Exception {

        if(userMapper.selectUserId(userId) == null) {
            throw new Exception("userId is not exist - userNameTest");
        }

        return userMapper.selectUserId(userId);
    }

    public UserInfoResponseDTO userInfo(String userId) throws Exception {
        List<UserInfoResponseVO> userInfoRes = userMapper.selectUserInfo(userId);
        if(userMapper.selectUserId(userId) == null) {
            throw new Exception("userId is not exist - userInfo");
        }

        UserInfoResponseDTO userInfoResDTO = new UserInfoResponseDTO();
        userInfoResDTO.setName(userInfoRes.get(0).getName());

        return userInfoResDTO;
    }
}