package com.tumblermall.login.controller;

import com.tumblermall.login.dto.LoginRequestDTO;
import com.tumblermall.login.service.LoginService;
import com.tumblermall.login.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String write() {
        return "/login/login";
    }

    @PostMapping("/login")
    public String write(LoginRequestDTO LoginRequestDTO, RedirectAttributes re, HttpSession session) {
        boolean res = loginService.loginCheck(LoginRequestDTO);

        if(!res){
            re.addFlashAttribute("errorMsg", "이메일 또는 비밀번호가 틀렸습니다.");
            return "redirect:/login";
        }
        LoginVO VO = loginService.logininfo(LoginRequestDTO.getUserEmail());
        session.setAttribute("userid",VO.getUserId());
        session.setAttribute("admin",VO.getIsAdmin());

        return "redirect:/main";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/mypage")
    public String mypage(HttpSession session) {
        if (session.getAttribute("userid") != null) {

            return "/login/my";
    }
        return "redirect:/login";

    }

    @GetMapping("/test")
    public String test(HttpSession session) {
        if (session.getAttribute("userid") != null) {
            return "redirect:/mypage";
        }

        return "redirect:/login";
    }



}
