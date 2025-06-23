package com.tumblermall.cart.controller;

import com.tumblermall.cart.dto.CartRequestDTO;
import com.tumblermall.cart.service.CartSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CartSelectController {
    @Autowired
    CartSelectService selectService;

    @GetMapping("/cart")
    public String goCart(){

        return "cart";
    }

    @PostMapping("/cart")
    public String showCart(){
        return "redirect:/login";
    }
}
