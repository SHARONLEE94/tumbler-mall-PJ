package com.tumblermall.products.dto;


public class ProductDTO {
    private Long id;
    private String name;
    private Integer price;
    private String releaseDate; // Date 타입으로 해도 무방
    private String categoryId;
    private String seriesId;
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSeriesId() { return seriesId; }

    public void setSeriesId(String seriesId) { this.seriesId = seriesId; }
}
