package com.tumblermall.order.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "상품 응답 객체")
public class ProductVo {
    @ApiModelProperty(value = "개별 제품 옵션 ID", example = "1001")
    private int productOptionId;

    @ApiModelProperty(value = "상품명", example = "아쿠아 텀블러 500ml")
    private String productName;

    @ApiModelProperty(value = "가격 (합산된 기본가 + 추가가)", example = "38000")
    private int price;

    @ApiModelProperty(value = "색상", example = "레드")
    private String color;

    @ApiModelProperty(value = "사이즈", example = "360mL")
    private String size;

    @ApiModelProperty(value = "상품 썸네일 파일 경로", example = "https://shop-phinf.pstatic.net/20250317_15/1742171755178UDtlA_JPEG/6974354318766909_716864716.jpg?type=m510")
    private String filePath;

    @ApiModelProperty(value = "주문 수량", example = "3")
    private int quantity;


    public int getProductOptionId() {
        return productOptionId;
    }

    public void setProductOptionId(int productOptionId) {
        this.productOptionId = productOptionId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
