package com.tumblermall.cart.controller;

import com.tumblermall.cart.dto.CartRequestDTO;
import com.tumblermall.cart.service.CartInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartInsertController {
    @Autowired
    CartInsertService insertService;

    @GetMapping("/cartinsert")
    public String goCart(){
        return "redirect:/cart";
    }

    @PostMapping("/cartinsert")
    public String showCart(CartRequestDTO requestDTO){

        insertService.goInsert(requestDTO);
        return "redirect:/cart";
    }

}
