package com.tumblermall.products.service;

import com.tumblermall.products.dto.ProductRequestDTO;
import com.tumblermall.products.mapper.ProductsMapper;
import com.tumblermall.products.vo.ProductRequestVO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    private final ProductsMapper productMapper;

    public ProductService(ProductsMapper productMapper) {
        this.productMapper = productMapper;
    }

    public ProductRequestDTO getProductDetail(Long productId) {
        ProductRequestDTO product = productMapper.selectProductById(productId);

        if (product == null) {
            return null;
        }

        List<ProductRequestVO> options = productMapper.selectOptionsByProductId(productId);
        product.setOptions(options);

        return product;
    }
}
