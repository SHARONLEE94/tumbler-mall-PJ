package com.tumblermall.cart.controller;

import com.tumblermall.cart.dto.CartRequestDTO;
import com.tumblermall.cart.service.CartSelectService;
import com.tumblermall.cart.vo.CartRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartSelectController {
    @Autowired
    CartSelectService selectService;

    @GetMapping("/cart")
    public String goCart(HttpSession session, Model model){
        //userId 변수에 세션 (userId)를 가져옴
        Integer userId = (Integer) session.getAttribute("userId");

        //로그인이 되어있지 않다면 로그인 창으로 이동
        if(userId == null) {
            return "login/login";
        }

        //로그인이 되어있다면 userId (cart 테이블에 user_id의 정보를 가져와서 list에 저장
        List<CartRequestVO> cartRequestVOList = selectService.checkedCart(userId);

        //list의 정보를 model 객체에 저장하여 view로 전달
        model.addAttribute("cartList", cartRequestVOList);

        return "product/cart";
    }

//    @PostMapping("/cart")
//    public String showCart(){
//        return "redirect:/login/login";
//    }
}
