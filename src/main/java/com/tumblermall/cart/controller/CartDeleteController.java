package com.tumblermall.cart.controller;

import com.tumblermall.cart.dto.CartDeleteDTO;
import com.tumblermall.cart.service.CartDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartDeleteController {
    @Autowired
    CartDeleteService deleteService;

//    @GetMapping("/cartdelete")
//    public String goDelete(){
//        return "redirect:/product/cart";
//    }

    @PostMapping("/cartdelete")
    public String deleteCart(CartDeleteDTO deleteDTO){
//        if((Integer)deleteDTO.getUserId() == null){
//            return null;
//        }
        deleteService.cartDeleteService(deleteDTO.getUserId(), deleteDTO.getProductOptionId());

        return "redirect:/cartTest";

    }
}

// Delete는 나중에 다 CRU 다 구현하고나서 UPDATE로 바꿔서 논리삭제 시킬지 결정. .jsp에서 <forEach>로 삭제 코딩 해야함.