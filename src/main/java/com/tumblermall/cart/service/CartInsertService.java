package com.tumblermall.cart.service;

import com.tumblermall.cart.dto.CartInsertDTO;
import com.tumblermall.cart.dto.CartRequestDTO;
import com.tumblermall.cart.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartInsertService {
    @Autowired
    CartMapper cartMapper;

    public int goInsert(CartRequestDTO insertDTO){

        return cartMapper.insertCart(insertDTO.getUserId(),
                                     insertDTO.getProductOptionId(),
                                     insertDTO.getProductCount());
    }
}
