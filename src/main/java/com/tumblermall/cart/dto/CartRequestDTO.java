package com.tumblermall.cart.dto;

import io.swagger.models.auth.In;

public class CartRequestDTO {
    private Integer userId;
    private Integer productOptionId;
    private Integer productCount;
    private Integer finalPrice;
    private Integer totalPrice;
    private Boolean cartCheckbox;

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

    public Integer getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Integer finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean getCartCheckbox() {
        return cartCheckbox;
    }

    public void setCartCheckbox(Boolean cartCheckbox) {
        this.cartCheckbox = cartCheckbox;
    }
}
