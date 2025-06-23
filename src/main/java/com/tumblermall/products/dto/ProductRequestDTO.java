package com.tumblermall.products.dto;

import com.tumblermall.products.vo.ProductRequestVO;

import java.util.List;

public class ProductRequestDTO {
    private Long id;
    private String name;
    private Integer price;
    private String releaseDate; // Date 타입으로 해도 무방
    private String discontinuedDate;
    private String shippingMethodId;
    private String shippingMethod;
    private List<ProductRequestVO> options;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductRequestVO> getOptions() {
        return options;
    }

    public void setOptions(List<ProductRequestVO> options) {
        this.options = options;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getDiscontinuedDate() {
        return discontinuedDate;
    }

    public void setDiscontinuedDate(String discontinuedDate) {
        this.discontinuedDate = discontinuedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getShippingMethodId() {
        return shippingMethodId;
    }

    public void setShippingMethodId(String shippingMethodId) {
        this.shippingMethodId = shippingMethodId;
    }
}
