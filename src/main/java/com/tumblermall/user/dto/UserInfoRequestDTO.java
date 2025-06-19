package com.tumblermall.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

// 화면 → 서버 요청
@ApiModel(description = "회원 가입 요청 DTO")
public class UserInfoRequestDTO {

    @ApiModelProperty(value = "이름", example = "홍길동", required = true)
    private String name;

    // Getter & Setter

    public String getName() {
        return name;
    }

    public String setName(String name) {
        this.name = name;
        return name;
    }
}
