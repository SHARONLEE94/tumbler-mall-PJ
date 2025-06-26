package com.tumblermall.cart.service;

import com.tumblermall.cart.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartUpdateService {
    @Autowired
    CartMapper mapper;

    public int checkedUpdate(int userId, int productOptionId, int productCount){
//         개별 제품 테이블의 최종 가격을 가져옴.
//        int basePrice = mapper.findPriceByProductOptionId(productOptionId);

//        개별 제품 최종 가격 * 제품의 개수 = 장바구니의 총 가격
//        int totalPrice = basePrice * productCount;
        return mapper.updateCart(productCount, 0 , userId, productOptionId);
    }

}
