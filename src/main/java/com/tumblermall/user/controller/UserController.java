package com.tumblermall.user.controller;

import com.tumblermall.user.dto.UserInfoRequestDTO;
import com.tumblermall.user.service.UserService;
import com.tumblermall.user.vo.UserInfoResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String register() {
        return "/user/register";
    }
}