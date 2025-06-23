package com.tumblermall.products.mapper;

import com.tumblermall.products.dto.ProductRequestDTO;
import com.tumblermall.products.vo.ProductRequestVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductsMapper {
    ProductRequestDTO selectProductById(@Param("productId") Long productId);

    List<ProductRequestVO> selectOptionsByProductId(@Param("productId") Long productId);
}
