package com.tumblermall.user.service;

import com.tumblermall.user.dto.UserRegRequestDTO;
import com.tumblermall.user.mapper.UserMapper;
import com.tumblermall.user.vo.UserInfoResponseVO;
import com.tumblermall.user.vo.UserRegVO;
import com.tumblermall.user.vo.EmailVerifyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder pwdEncoder;
    @Autowired
    private JavaMailSender mailSender;

    int token;

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

    }
    // 이메일, 인증코드 검증
    public boolean register(UserRegRequestDTO userRegRequestDTO) {
        try {
            EmailVerifyVO emailverifyVO = new EmailVerifyVO();
            emailverifyVO.setEmail(userRegRequestDTO.getUserEmail());
            System.out.println("나는 이동재. 바보입니다 =>" + userRegRequestDTO.getUserEmail());
            EmailVerifyVO verifyData = userMapper.selectEmailVerify(emailverifyVO.getEmail());

            if (userRegRequestDTO.getEmailVerifyCode() == verifyData.getToken()) {
                pwdEncoder(userRegRequestDTO);
                userMapper.insertUserReg(userRegRequestDTO);
               userMapper.delectEmailVerify(verifyData.getEmail());
               // delete가 안됐으면?????????
               // return이 무조건 타는거야 이거 주의해야해
                // 어떻게해야 무조건 타지 않을 수 있는지 고민해봐야
               return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }

        return false;
    }

    // 이메일 중복검사 및 인증코드 전송
    public boolean mailConfirm(String email) throws Exception {
        System.out.println("mailConfirm!@#!@#+++++++++++++++++");
        UserRegVO userRegVO = new UserRegVO();
        userRegVO.setUserEmail(email);

        int emailCount = userMapper.selectUserEmail(userRegVO);

        System.out.println("emailcount"+emailCount);

        if(emailCount == 0){
            // 중복없음. 생성가능, 인증메일 전송
            System.out.println("@@@@@ verify true @@@@@@@");

            mailAuthCode(email);
            SimpleMailMessage message = new SimpleMailMessage();
            System.out.println("@@@@@ verify true222222 @@@@@@@");

            message.setTo(email);
            message.setSubject("가입 인증번호");
            message.setText("인증번호 : "+ token);
            message.setFrom("djae8888@gmail.com"); // 보내는 이메일 주소

            mailSender.send(message);

            return true;
        }
        else {
            System.out.println("@@@@@ verify false @@@@@@@");
            //성성불가, 인증 전송x
        return  false;
        }
    }

    // 인증코드 생성, 인증정보 insert
    public void mailAuthCode(String email)  {

        System.out.println("mailAuthCode ====@!@#!@#@@@!#!@#!@# ");

        try {
            this.token = new Random().nextInt(900000) + 100000;
            EmailVerifyVO verifyVO = new EmailVerifyVO();
            Timestamp expireAt = Timestamp.valueOf(LocalDateTime.now().plusMinutes(5));

            verifyVO.setEmail(email);
            verifyVO.setToken(token);
            verifyVO.setExpireAt(expireAt);
            verifyVO.setVerified(false);

            userMapper.insertEmailVerify(verifyVO);

        }
        catch (Exception e){
            System.out.println("에에에에에에ㅔ엥에에엥에에sefefkrjweklfjoawdfjlaksfjlakef");
        }

    }

}