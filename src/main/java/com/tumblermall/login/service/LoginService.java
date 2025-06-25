package com.tumblermall.login.service;

import com.tumblermall.login.dto.LoginRequestDTO;
import com.tumblermall.login.mapper.LoginMapper;
import com.tumblermall.login.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginVO logininfo(String email) {
        return loginMapper.selectLogin(email);
    }

    public boolean loginCheck(LoginRequestDTO LoginRequestDTO) {
        LoginVO LoginVO = loginMapper.selectLogin(LoginRequestDTO.getUserEmail());

        return passwordEncoder.matches(LoginRequestDTO.getUserPwd(),  LoginVO.getUserPwd());
    }
}
