package com.tumblermall.cart.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "장바구니 상품 정보 요청 DTO")
public class CartRequestDTO {

    @ApiModelProperty(value = "사용자 ID", example = "1", required = true)
    private Integer userId;

    @ApiModelProperty(value = "상품 옵션 ID", example = "10", required = true)
    private Integer productOptionId;

    @ApiModelProperty(value = "상품 수량", example = "2", required = true)
    private Integer productCount;

    @ApiModelProperty(value = "상품의 단일 가격", example = "15000", required = true)
    private Integer finalPrice;

    @ApiModelProperty(value = "상품 수량 * 가격의 총합", example = "30000", required = true)
    private Integer totalPrice;

    @ApiModelProperty(value = "체크박스 선택 여부 (true: 선택, false: 미선택)", example = "true", required = true)
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
