package com.tumblermall.cart.mapper;

import com.tumblermall.cart.vo.CartRequestVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartMapper {
    List<CartRequestVO> selectCart(@Param("userId") int userId);

    int deleteCart(@Param("userId") int userId, @Param("productOptionId") int productOptionId);

    int updateCart(@Param("productCount") int productCount,
                   @Param("totalPrice") int totalPrice,
                   @Param("userId") int userId,
                   @Param("productOptionId") int productOptionId);

    int insertCart(@Param("userId") int userId,
                   @Param("productOptionId") int productOptionId,
                   @Param("productCount") int productCoun);
}
