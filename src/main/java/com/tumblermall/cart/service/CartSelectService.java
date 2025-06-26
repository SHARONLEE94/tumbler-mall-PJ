package com.tumblermall.cart.service;

import com.tumblermall.cart.dto.CartRequestDTO;
import com.tumblermall.cart.mapper.CartMapper;
import com.tumblermall.cart.vo.CartRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartSelectService {
    @Autowired
    CartMapper cartMapper;

    // 나중에 예외 발생 시 예외처리 추가.
    public List<CartRequestVO> checkedCart(int userId) {
            return cartMapper.selectCart(userId);
    }

    public void updateCheckbox(CartRequestDTO dto){
        cartMapper.updateCartCheckbox(dto);
    }
}
