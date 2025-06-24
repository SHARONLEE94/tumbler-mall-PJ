package com.tumblermall.products.controller;

import com.tumblermall.products.dto.ProductRequestDTO;
import com.tumblermall.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String products() {
        return "/product/products";
    }
    // 상품 상세 페이지
    @GetMapping("/productDetl")
    public String index(@RequestParam("productId") Long productId, Model model) {
        // Service 통해 상품 정보 + 옵션 리스트 조회
        ProductRequestDTO product = productService.getProductDetail(productId);
        System.out.println("조회된 상품: " + product);
        // 모델에 담아 뷰로 전달
        model.addAttribute("product", product);

        return "/product/productDetl";
    }
}
