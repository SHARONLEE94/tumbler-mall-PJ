package com.tumblermall.cart.service;

import com.tumblermall.cart.mapper.CartMapper;
import com.tumblermall.cart.vo.CartRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartSelectService {
    @Autowired
    CartMapper cartMapper;

    public List<CartRequestVO> checkedCart(int userId) {
        if(userId != null){
            return checkedCart(userId);
        }
    }
}
