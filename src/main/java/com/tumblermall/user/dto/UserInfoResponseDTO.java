package com.tumblermall.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

// 서버 → 화면 응답
@ApiModel(description = "회원 정보 응답 DTO")
public class UserInfoResponseDTO {

    @ApiModelProperty(value = "회원 ID", example = "1")
    private Long userId;

    @ApiModelProperty(value = "이메일", example = "user@example.com")
    private String email;

    @ApiModelProperty(value = "이름", example = "홍길동")
    private String name;

    // Getter & Setter
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
