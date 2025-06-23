package com.tumblermall.user.service;

import com.tumblermall.user.dto.UserRegRequestDTO;
import com.tumblermall.user.mapper.UserMapper;
import com.tumblermall.user.vo.UserInfoResponseVO;
import com.tumblermall.user.vo.UserRegVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder pwdEncoder;
    @Autowired
    private JavaMailSender mailSender;

    public String userNameTest(String userId) throws Exception {

        if(userMapper.selectUserId(userId) == null) {
            throw new Exception("userId is not exist - userNameTest");
        }

        return userMapper.selectUserId(userId);
    }

    public List<UserInfoResponseVO> userInfo(String userId) throws Exception {

        if(userMapper.selectUserId(userId) == null) {
            throw new Exception("userId is not exist - userInfo");
        }

        return userMapper.selectUserInfo(userId);
    }


    public void pwdEncoder(UserRegRequestDTO userRegRequestDTO) throws Exception {
        String pwd = pwdEncoder.encode(userRegRequestDTO.getUserPwd());
        userRegRequestDTO.setUserPwd(pwd);
//       mapper.insertUser(user);
    }

    public void register(UserRegRequestDTO userRegRequestDTO) throws Exception {
        UserRegVO userRegVO = new UserRegVO();
        userRegVO.setUserEmail(userRegRequestDTO.getUserEmail());
        userMapper.selectUserEmail(userRegVO);
        System.out.println("emailcount"+userMapper.selectUserEmail(userRegVO));

        if(userMapper.selectUserEmail(userRegVO) == 0){
            // 중복없음. 생성가능, 인증메일 전송
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(userRegRequestDTO.getUserEmail());
            message.setSubject("가입 인증번호");
            message.setText("인증번호 : ");
            message.setFrom("djae8888@gmail.com"); // 보내는 이메일 주소

            mailSender.send(message);
        }
        else {
            //성성불가, 인증 전송x
        }
    }

    public void mailConfirm(){

    }

}