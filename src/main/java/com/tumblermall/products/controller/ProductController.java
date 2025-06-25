package com.tumblermall.products.controller;

import com.tumblermall.products.dto.ProductDTO;
import com.tumblermall.products.dto.ProductDetailDTO;
import com.tumblermall.products.dto.ProductResponseDTO;
import com.tumblermall.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String products() {
        return "/product/products";
    }
    // 상품 상세 페이지
    @GetMapping("/productDetl")
    public String index(@RequestParam("productId") Long productId, Model model) {
        //List<ProductDTO> product = productService.getProduct(productId);
        //List<ProductDetailDTO> productDetail = productService.getProductDetail(productId);

        List<ProductResponseDTO> productResponse = productService.getProductResponse(productId);
        model.addAttribute("productResponse", productResponse);
        //model.addAttribute("product", product);
        //model.addAttribute("productDetail", productDetail);
        return "/product/productDetl";
    }

    @PostMapping("/cart/add")
    public Void addToCart(
            @RequestParam("productId") String productId,
            @RequestParam("color") String color,
            @RequestParam("size") String size,
            @RequestParam("quantity") int quantity
    ) {
        // 입력 값 정제
        String sanitizedColor = sanitize(color); // 색상 값 정제
        String sanitizedSize = sanitize(size);   // 사이즈 값 정제
        int sanitizedQuantity = Math.max(1, quantity); // 수량은 1 이상으로 제한

        Map<String, Object> cartCheck = new HashMap<>();
        cartCheck.put("productId", productId);
        cartCheck.put("color", sanitizedColor);
        cartCheck.put("size", sanitizedSize);
        cartCheck.put("quantity", sanitizedQuantity);

        System.out.println("[AddToCart] " + cartCheck);
        return null;
    }

    // 입력 값 정제 메서드
    private String sanitize(String input) {
        if (input == null) {
            return "";
        }
        // XSS 방지 및 불필요한 공백 제거
        return input.trim().replaceAll("[<>\"&']", "");
    }
}
