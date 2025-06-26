package com.tumblermall.user.controller;

import com.tumblermall.user.dto.UserInfoRequestDTO;
import com.tumblermall.user.dto.UserRegRequestDTO;
import com.tumblermall.user.mapper.UserMapper;
import com.tumblermall.user.service.UserService;
import com.tumblermall.user.vo.UserInfoResponseVO;
import com.tumblermall.user.vo.UserRegVO;
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

    @GetMapping("/register")
    public String registerForm(){

        return "/user/register";
    }

    @PostMapping("/register")
    public String register(UserRegRequestDTO userRegRequestDTO, RedirectAttributes redirectAttributes) throws Exception {
        if(userService.register(userRegRequestDTO)){
            return "redirect:/login";
        }else {
            redirectAttributes.addFlashAttribute("errorMsg", "인증번호를 확인해 주세요");
            return "redirect:/register";
        }
    }

    @PostMapping(value="/sendEmail", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> sendEmail(@RequestParam String userEmail) throws Exception {

    return userService.mailConfirm(userEmail) ? ResponseEntity.ok("이메일 전송 완료") : ResponseEntity.ok("이미 사용 중인 메일입니다.") ;
    }
}