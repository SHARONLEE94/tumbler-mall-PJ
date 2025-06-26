package com.tumblermall.products.controller;


import com.tumblermall.cart.dto.CartRequestDTO;
import com.tumblermall.cart.service.CartInsertService;
import com.tumblermall.order.vo.ProductVo;
import com.tumblermall.products.dto.ProductDetailDTO;
import com.tumblermall.products.dto.ProductResponseDTO;
import com.tumblermall.products.service.ProductService;
import com.tumblermall.products.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;


@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartInsertService cartInsertService;
    private CartRequestDTO cartRequestDTO;

    @GetMapping("/products")
    public String products() {
        return "/product/products";
    }
    // todo
    /*@GetMapping("/products")
    public String getProductsByCategory(@RequestParam("category") String categoryId, Model model) {
        System.out.println("\n\n\ncategoryId\n\n\n" + categoryId);
        List<ProductVO> products = productService.getProductsByCategoryId(categoryId);
        System.out.println(products);
        model.addAttribute("products", products);
        return "product/products";
    }

    @GetMapping("/series")
    public String getProductsBySeries(@RequestParam("seriesId") String seriesId, Model model) {
        List<ProductVO> products = productService.getProductsBySeriesId(seriesId);
        model.addAttribute("products", products);
        return "product/products";
    }*/






    @GetMapping("/productDetl")
    public String index(@RequestParam("productId") Long productId, Model model) {
        List<ProductResponseDTO> productResponse = productService.getProductResponse(productId);
        model.addAttribute("productResponse", productResponse);

        return "/product/productDetl";
    }








    @PostMapping("/prdDetlCartUpdate")
    public String addToCart(
            @RequestParam("productId") String productId,
            @RequestParam("color") String color,
            @RequestParam("size") String size,
            @RequestParam("quantity") int quantity,
            //HttpSession session
            Model m
    ) {
        int userId = 1;
        m.addAttribute("userId",userId);

        // 입력 값 정제
        String sanitizedColor = sanitize(color);
        String sanitizedSize = sanitize(size);
        int sanitizedQuantity = Math.max(1, quantity);

        String productOptionId = productService.getProductOptionId(productId, sanitizedColor, sanitizedSize);

        CartRequestDTO cartRequestDTO = new CartRequestDTO();

        cartRequestDTO.setUserId(userId);
        cartRequestDTO.setProductOptionId(Integer.parseInt(productOptionId));
        cartRequestDTO.setProductCount(sanitizedQuantity);

        cartInsertService.goInsert(cartRequestDTO);
        System.out.println(cartRequestDTO);
        return "redirect:/cart";
    }

    @PostMapping("/order1")
    public String addToOrder1(
            @RequestParam("productId") String productId,
            @RequestParam("color") String color,
            @RequestParam("size") String size,
            @RequestParam("quantity") int quantity,
            Model m
    ){
        ProductDetailDTO detailDTO = new ProductDetailDTO();
        ProductVo productVo = new ProductVo();

        String sanitizedColor = sanitize(color);
        String sanitizedSize = sanitize(size);
        int sanitizedQuantity = Math.max(1, quantity);
        int basePrice = productVo.getPrice();
        int additionalPrice = productVo.getPrice();
        int totalPrice = (basePrice + additionalPrice) * sanitizedQuantity;

        cartRequestDTO.setTotalPrice(totalPrice);

        String productOptionId = productService.getProductOptionId(productId, sanitizedColor, sanitizedSize);

        String items = productOptionId + ":" + sanitizedQuantity;

        System.out.println("[AddToOrder] " + items);
        m.addAttribute("userId",6);
        m.addAttribute("items", items);
        return "redirect:/order";
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
