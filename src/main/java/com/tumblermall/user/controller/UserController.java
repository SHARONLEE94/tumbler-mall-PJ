package com.tumblermall.user.controller;

import com.tumblermall.user.dto.UserInfoRequestDTO;
import com.tumblermall.user.dto.UserRegRequestDTO;
import com.tumblermall.user.mapper.UserMapper;
import com.tumblermall.user.service.UserService;
import com.tumblermall.user.vo.UserInfoResponseVO;
import com.tumblermall.user.vo.UserRegVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserService userService;


    // http://localhost:8080/user?userId=1
    @GetMapping("/user")
    public String user(HttpServletRequest request, Model model) {

        try {
            String userIdVal = request.getParameter("userId");
            UserInfoRequestDTO userInfo = new UserInfoRequestDTO();
            String userId = userInfo.setName(userIdVal);

            if (userId == null) {
                throw new Exception("userId is Null");
            }

            String userName = userService.userNameTest(userId);

            model.addAttribute("userName", userName);
            return "/user/user";

        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return "/common/error";
        }
    }

    // http://localhost:8080/userInfo?userId=1
    @GetMapping("/userInfo")
    public String userInfo(HttpServletRequest request, Model model) {

        try {
            String userIdVal = request.getParameter("userId");
            UserInfoRequestDTO userInfo = new UserInfoRequestDTO();
            String userId = userInfo.setName(userIdVal);

            if (userId == null) {
                throw new Exception("userId is Null");
            }
            List<UserInfoResponseVO> userInfoRes = userService.userInfo(userId);

            model.addAttribute("userInfoRes", userInfoRes);
            return "/user/userInfo";

        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return "/common/error";
        }
    }
    @ApiOperation(value = "회원가입 페이지",
            notes = " 회원가입 페이지 <br/><br/>")
    @GetMapping("/register")
    public String registerForm(){

        return "/user/register";
    }

    @ApiOperation(value = "회원가입 요청",
            notes = "고객이 입력한 정보로 회원가입 요청<br/><br/>"
                    +  "- request param : userEmail <br/>"
                    +  "- request param : userPwd <br/>"
                    +  "- request param : emailVerifyCode <br/>"
                    +  "- request param : userAddress <br/>"
                    +  "- request param : userBirthDate <br/>"
                    +  "- request param : userGenderId <br/>"
                    +  "- request param : userIsAdReceive <br/>"
                    +  "- request param : userJobId <br/>"
                    +  "- request param : userName <br/>"
                    +  "- request param : userPhone <br/>"
                    +  "- request param : userPostalCode <br/>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userEmail", value = "사용자 이메일", required = true, dataType = "string", paramType = "query", example = "user@example.com"),
            @ApiImplicitParam(name = "userPwd", value = "사용자 비밀번호", required = true, dataType = "string", paramType = "query", example = "12345678"),
            @ApiImplicitParam(name = "emailVerifyCode", value = "인증코드", required = true, dataType = "int", paramType = "query", example = "992912"),
            @ApiImplicitParam(name = "userAddress", value = "주소", required = true, dataType = "string", paramType = "query", example = "서울 강남구 강남대로 364"),
            @ApiImplicitParam(name = "userAddressDetail", value = "상세주소", required = true, dataType = "string", paramType = "query", example = "10층"),
            @ApiImplicitParam(name = "userBirthDate", value = "생일", required = true, dataType = "date", paramType = "query", example = "1200-11-11"),
            @ApiImplicitParam(name = "userGenderId", value = "성별", required = true, dataType = "string", paramType = "query", example = "M"),
            @ApiImplicitParam(name = "userIsAdReceive", value = "광고여부", required = true, dataType = "string", paramType = "query", example = "1"),
            @ApiImplicitParam(name = "userJobId", value = "직업", required = true, dataType = "string", paramType = "query", example = "1"),
            @ApiImplicitParam(name = "userName", value = "이름", required = true, dataType = "string", paramType = "query", example = "홍길동"),
            @ApiImplicitParam(name = "userPhone", value = "연락처", required = true, dataType = "string", paramType = "query", example = "010-1234-1234"),
            @ApiImplicitParam(name = "userPostalCode", value = "우편번호", required = true, dataType = "string", paramType = "query", example = "382992")
    })
    @PostMapping("/register")
    public String register(UserRegRequestDTO userRegRequestDTO, RedirectAttributes redirectAttributes) throws Exception {
        if(userService.register(userRegRequestDTO)){
            return "redirect:/login";
        }else {
            redirectAttributes.addFlashAttribute("errorMsg", "인증번호를 확인해 주세요");
            return "redirect:/register";
        }
    }

    @ApiOperation(
            value = "이메일 인증 요청",
            notes = "설명: 회원가입 시 입력한 이메일로 인증번호를 전송합니다.<br/><br/>"
                    + "1. 입력한 이메일이 중복되지 않았을 경우 인증번호를 이메일로 전송<br/>"
                    + "2. 이미 사용 중인 이메일일 경우 전송하지 않음"
    )
    @ApiImplicitParam(name = "userEmail", value = "사용자가 입력한 이메일 주소", required = true, dataType = "String", paramType = "query", example = "example@example.com"
    )
    @PostMapping(value="/sendEmail", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> sendEmail(@RequestParam String userEmail) throws Exception {

    return userService.mailConfirm(userEmail) ? ResponseEntity.ok("이메일 전송 완료") : ResponseEntity.ok("이미 사용 중인 메일입니다.") ;
    }
}