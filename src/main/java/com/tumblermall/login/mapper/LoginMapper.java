package com.tumblermall.login.mapper;

import com.tumblermall.login.vo.LoginVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    LoginVO selectLogin(String userEmail);
    int selectLoginEmail(String userEmail);
}
