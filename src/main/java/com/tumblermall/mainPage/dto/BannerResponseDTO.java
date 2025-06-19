package com.tumblermall.mainPage.dto;

import io.swagger.annotations.*;

@ApiModel(description = "메인 배너 응답 DTO")
public class BannerResponseDTO {

    @ApiModelProperty(value = "배너 ID", example = "1")
    private Long bannerId;

    @ApiModelProperty(value = "배너 타이틀", example = "신상품 할인 이벤트")
    private String title;

    @ApiModelProperty(value = "이미지 URL", example = "https://cdn.example.com/banner1.jpg")
    private String imageUrl;

    @ApiModelProperty(value = "배너 클릭 시 이동할 링크", example = "/products/1")
    private String link;
}
