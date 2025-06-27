package com.tumblermall.cart.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "장바구니에서 상품을 삭제할 때 사용하는 DTO")
public class CartDeleteDTO {

    @ApiModelProperty(value = "사용자 ID", example = "1", required = true)
    private int userId;

    @ApiModelProperty(value = "삭제할 상품의 옵션 ID", example = "5", required = true)
    private int productOptionId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductOptionId() {
        return productOptionId;
    }

    public void setProductOptionId(int productOptionId) {
        this.productOptionId = productOptionId;
    }
}
