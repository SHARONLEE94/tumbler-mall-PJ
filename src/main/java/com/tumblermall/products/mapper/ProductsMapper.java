package com.tumblermall.products.mapper;

import com.tumblermall.products.vo.ProductDetailVO;
import com.tumblermall.products.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductsMapper {
    List<ProductVO> selectProductById(@Param("productId") Long productId);

    List<ProductDetailVO> selectDetailsByProductId(@Param("productId") Long productId);

    List<ProductVO> selectProductsByCategoryId(@Param("categoryId") String categoryId);

    List<ProductVO> selectProductsBySeriesId(@Param("seriesId") String seriesId);
}
