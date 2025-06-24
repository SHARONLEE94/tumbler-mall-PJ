package com.tumblermall.cart.service;

import com.tumblermall.cart.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartDeleteService {
    @Autowired
    CartMapper mapper;

    public int cartDeleteService(int userId, int productOptionId){
        return mapper.deleteCart(userId, productOptionId);
    }
}
