package com.tumblermall.cart.mapper;

import com.tumblermall.cart.vo.CartRequestVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartMapper {
    List<CartRequestVO> selectCart(@Param("userId") int userId);

    int deleteCart(@Param("userId") int userId, @Param("productOptionId") int productOptionId);

    int updateCart(@Param("productCount") int productCount, @Param("userId") int userId, @Param("productOptionId") int productOptionId);
}
