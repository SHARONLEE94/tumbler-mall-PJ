package com.tumblermall.user.controller;

import com.tumblermall.user.dto.UserInfoRequestDTO;
import com.tumblermall.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String user(HttpServletRequest request, Model model) {

       String userIdVal = request.getParameter("userId");

        UserInfoRequestDTO userInfo = null;
        String userId = userInfo.setName(userIdVal);

        String userName = userService.userNameTest(userId);

        if(!"".equals(userName)) {
            return "/common/error";
        }

        model.addAttribute("userName",userService.userNameTest(userId));

        return "/user/user";
    }
}
