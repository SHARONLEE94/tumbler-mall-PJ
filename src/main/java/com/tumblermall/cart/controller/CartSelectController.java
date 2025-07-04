package com.tumblermall.cart.controller;

import com.tumblermall.cart.dto.CartRequestDTO;
import com.tumblermall.cart.service.CartSelectService;
import com.tumblermall.cart.vo.CartRequestVO;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "장바구니 페이지",
            notes = "설명 : 첫번째 토이 프로젝트 장바구니 조회 <br/><br/>")

    @GetMapping("/cart")
    public String goCart(HttpSession session, Model model) {
//        int userId = 1;

        //userId 변수에 세션 (userId)를 가져옴
        Integer userId = (Integer) session.getAttribute("userId");

        //로그인이 되어있지 않다면 로그인 창으로 이동
        if (userId == null) {
            model.addAttribute("cartloginMessage", "로그인이 필요합니다.");
            return "login/login";
        }
            //로그인이 되어있다면 userId (cart 테이블에 user_id의 정보를 가져와서 list에 저장
        List<CartRequestVO> cartRequestVOList = selectService.checkedCart(userId);

            //list의 정보를 model 객체에 저장하여 view로 전달
        model.addAttribute("cartList", cartRequestVOList);

        return "/product/cart";
    }



        @ApiOperation(value = "장바구니 페이지",
            notes = "설명 : 첫번째 토이 프로젝트 장바구니 제품 별 체크박스 <br/><br/>"
                    + "- request param : CartRequestDTO<br/>")

        //체크박스 확인
        @PostMapping("/cartCheckboxUpdate")
        public String showCartCheckbox (CartRequestDTO dto) {
            selectService.updateCheckbox(dto);
            return "redirect:/cart";
        }
}
