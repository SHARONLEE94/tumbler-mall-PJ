package com.tumblermall.cart.controller;

import com.tumblermall.cart.dto.CartRequestDTO;
import com.tumblermall.cart.service.CartUpdateService;
import com.tumblermall.cart.vo.CartRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CartUpdateController {
    @Autowired
    CartUpdateService updateService;

    @GetMapping("/cartupdate")
    public String showCart(Model model, CartRequestDTO requestDTO){


        return "redirect:/cart";
    }

    @PostMapping("/cartupdate")
    public String updateCart(CartRequestDTO requestDTO, Model model) {
        try {
            updateService.checkedUpdate(requestDTO.getUserId(),
                    requestDTO.getProductOptionId(),
                    requestDTO.getProductCount());
        } catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/cart";
    }
}
