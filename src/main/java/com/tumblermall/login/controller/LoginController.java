package com.tumblermall.login.controller;

import com.tumblermall.login.dto.LoginRequestDTO;
import com.tumblermall.login.service.LoginService;
import com.tumblermall.login.vo.LoginVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "로그인 페이지",
            notes = "설명: 회원 로그인 페이지  <br/><br/>"
                    +  "- request param : 없음 <br/>")
    @GetMapping("/login")
    public String write() {
        return "/login/login";
    }

    @ApiOperation(value = "로그인 요청",
            notes = "이메일, 비밀번호로 로그인 요청을 처리  <br/><br/>"
                    +  "- request param : userEmail <br/>"
                    +  "- request param : userPwd <br/>")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userEmail", value = "사용자 이메일", required = true, dataType = "string", paramType = "query", example = "user@example.com"),
            @ApiImplicitParam(name = "userPwd", value = "사용자 비밀번호", required = true, dataType = "string", paramType = "query", example = "12345678")
    })
    @PostMapping("/login")
    public String write(LoginRequestDTO LoginRequestDTO, RedirectAttributes re, HttpSession session) {
        boolean res = loginService.loginCheck(LoginRequestDTO);

        if(!res){
            re.addFlashAttribute("errorMsg", "이메일 또는 비밀번호가 틀렸습니다.");
            return "redirect:/login";
        }
        LoginVO VO = loginService.logininfo(LoginRequestDTO.getUserEmail());
        session.setAttribute("userId",VO.getUserId());
        session.setAttribute("admin",VO.getIsAdmin());

        return "redirect:/main";
    }


    @ApiOperation(value = "로그아웃 요청",
            notes = "세션 삭제 후 로그아웃 처리  <br/><br/>"
                    +  "- request param : 없음 <br/>")
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @ApiOperation(value = "마이페이지 진입 ",
            notes = "로그인된 사용자의 마이페이지로 접근<br/>"
            + "1. 세션 userId가 있으면 /login/my.jsp로 이동"
            + "2. 세션에 값이 없으면 /login으로 리다이렉트")
    @GetMapping("/mypage")
    public String mypage(HttpSession session) {
        if (session.getAttribute("userId") != null) {

            return "/login/my";
    }
        return "redirect:/login";

    }
    @ApiOperation(value = "마이페이지 이동 ",
            notes = "마이페이지 이동<br/><br/>"
            + "1. 세션에 userId가 존재하면 `/login/my` 페이지로 이동<br/>"
            + "2. 존재하지 않으면 로그인 페이지로 리다이렉트")
    @GetMapping("/gopage")
    public String goPage(HttpSession session) {
        if (session.getAttribute("userId") != null) {
            return "redirect:/mypage";
        }
        return "redirect:/login";
    }
}
