package com.tumblermall.cart.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "장바구니에 상품을 추가할 때 사용하는 DTO")
public class CartInsertDTO {

    @ApiModelProperty(value = "사용자 ID", example = "1", required = true)
    private Integer userId;

    @ApiModelProperty(value = "추가할 상품의 옵션 ID", example = "10", required = true)
    private Integer productOptionId;

    @ApiModelProperty(value = "상품 수량", example = "2", required = true)
    private Integer productCount;

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
}
