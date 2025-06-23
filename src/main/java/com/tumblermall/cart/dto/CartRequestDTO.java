package com.tumblermall.cart.dto;

public class CartRequestDTO {
    private Integer userId;
    private Integer productOptionId;
    private Integer productCount;
    private String productYN;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductOptionId() {
        return productOptionId;
    }

    public void setProductOptionId(Integer productOptionId) {
        this.productOptionId = productOptionId;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public String getProductYN() {
        return productYN;
    }

    public void setProductYN(String productYN) {
        this.productYN = productYN;
    }
}
