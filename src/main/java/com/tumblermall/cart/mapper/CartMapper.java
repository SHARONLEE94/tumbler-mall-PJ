package com.tumblermall.cart.mapper;

import com.tumblermall.cart.vo.CartRequestVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartMapper {
    List<CartRequestVO> selectByUserCart(@Param("userId") int userId);


}
