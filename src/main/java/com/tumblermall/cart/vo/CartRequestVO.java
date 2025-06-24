package com.tumblermall.cart.vo;

public class CartRequestVO {
    private Integer userId;
    private Integer productOptionId;
    private Integer productCount;
    private String productYN;
    private String color;
    private String size;
    private int finalPrice;
    private String productName;

    public CartRequestVO(Integer userId,
                         Integer productOptionId,
                         Integer productCount,
                         String productYN,
                         String color,
                         String size,
                         Integer finalPrice,
                         String productName) {
        this.userId = userId;
        this.productOptionId = productOptionId;
        this.productCount = productCount;
        this.productYN = productYN;
        this.color = color;
        this.size = size;
        this.finalPrice = finalPrice;
        this.productName = productName;
    }


    // Getter & Setter
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setProductOptionId(Integer productOptionId) {
        this.productOptionId = productOptionId;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public void setProductYN(String productYN) {
        this.productYN = productYN;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setFinalPrice(Integer finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getProductOptionId() {
        return productOptionId;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public String getProductYN() {
        return productYN;
    }

    public String getProductName() {
        return productName;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public Integer getFinalPrice() {
        return finalPrice;
    }
}
