package com.tumblermall.cart.controller;

import com.tumblermall.cart.dto.CartRequestDTO;
import com.tumblermall.cart.service.CartInsertService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartInsertController {
    @Autowired
    CartInsertService insertService;

    @ApiOperation(value = "제품 상세 페이지",
            notes = "설명 : 첫번째 토이 프로젝트 장바구니 담기 <br/><br/>"
                    + "- request param : CartDeleteDTO<br/>")

    @GetMapping("/cartinsert")
    public String goCart(){
        return "redirect:/cart";
    }

    @PostMapping("/cartinsert")
    public String showCart(CartRequestDTO requestDTO){
        try {
            insertService.goInsert(requestDTO);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/cart";
    }

}
