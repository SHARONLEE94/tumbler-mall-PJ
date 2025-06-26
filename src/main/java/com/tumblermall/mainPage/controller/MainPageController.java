package com.tumblermall.mainPage.controller;

import com.tumblermall.global.exception.ApiResponse;
import com.tumblermall.mainPage.dto.BannerResponseDTO;
import com.tumblermall.mainPage.service.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "01. Main Page")
@Controller
public class MainPageController {

    @Autowired
    private BannerService bannerService;

    @ApiOperation(value = "메인 페이지",
                  notes = "설명: 첫번째 토이 프로젝트 메인 페이지 조회 <br/><br/>"
                        +  "- request param : 없음 <br/>")
    @GetMapping(value = "/main",  produces = "application/json; charset=utf8")
    public String bannerPage(Model model) {
        model.addAttribute("banners", bannerService.getBanners());
        return "main/mainPage"; // JSP 페이지 반환
    }

    @ApiOperation(value = "배너 리스트 API (Ajax)",
                  notes = "설명: 첫번째 토이 프로젝트 메인 페이지 조회 <br/><br/>"
                        + "- request param : example1 <br/>"
                        + "- request param : example2 <br/>"
                        + "- request param : example3")
    @GetMapping("/api/banners")
    @ResponseBody
    public ApiResponse<List<BannerResponseDTO>> getBanners() {
        int banners = bannerService.getBanners();

        List<BannerResponseDTO> bannersList = new ArrayList<>();

        if(banners == 1) {
            return ApiResponse.fail(null);
        } else {
            return ApiResponse.success(bannersList);
        }
    }
}