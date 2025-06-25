package com.tumblermall.products.dto;

import java.util.List;

public class ProductResponseDTO {
    private ProductDTO product;
    private List<ProductDetailDTO> productDetail;

    public ProductResponseDTO(ProductDTO product, List<ProductDetailDTO> productDetail) {
        this.product = product;
        this.productDetail = productDetail;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public List<ProductDetailDTO> getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(List<ProductDetailDTO> productDetail) {
        this.productDetail = productDetail;
    }
}

