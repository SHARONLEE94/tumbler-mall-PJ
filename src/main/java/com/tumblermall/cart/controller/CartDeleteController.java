package com.tumblermall.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class CartDeleteController {
    @Autowired
    CartDeleteService deleteService;

    @GetMapping("/cart/delete")
    public String write(){
        return "redirect/cart";
    }
}
