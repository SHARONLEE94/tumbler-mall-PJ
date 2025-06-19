package com.tumblermall.mainPage.service;

import com.tumblermall.mainPage.dto.BannerResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BannerService {

    public int getBanners() {
        List<BannerResponseDTO> banners = new ArrayList<>();

        if(banners == null) {
            return 1; // [수정필요]여기 예외생성해서 던저야해
        }
        return 2;
    };
}
