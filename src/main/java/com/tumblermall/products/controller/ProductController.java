package com.tumblermall.products.controller;


import com.tumblermall.cart.dto.CartRequestDTO;
import com.tumblermall.cart.service.CartInsertService;
import com.tumblermall.order.vo.ProductVo;
import com.tumblermall.products.dto.ProductDetailDTO;
import com.tumblermall.products.dto.ProductResponseDTO;
import com.tumblermall.products.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import java.util.List;

@Api(value = "상품 관련 API")
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

    @ApiOperation(
            value = "상품 상세 조회",
            notes = "설명 : 상품 ID를 기반으로 컬러 사이즈 등의 상세 정보를 조회<br/><br/>"
                    + "API 사용 목록<br/>"
                    + "1. 상품 기본 정보 및 옵션 출력<br/>"
                    + "- request param : productId (상품 ID)"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "상품 ID", required = true, dataType = "long", paramType = "query", example = "1001")
    })
    @GetMapping("/productDetl")
    public String productDetl(@RequestParam("productId") Long productId, Model model) {
        List<ProductResponseDTO> productResponse = productService.getProductResponse(productId);
        model.addAttribute("productResponse", productResponse);

        return "/product/productDetl";
    }


    @ApiOperation(
            value = "장바구니 추가",
            notes = "설명 : 상품 옵션을 선택하고 수량을 정해 장바구니에 추가<br/><br/>"
                    + "API 사용 목록<br/>"
                    + "1. 상품 ID, 색상, 사이즈, 수량을 받아온다.<br/>"
                    + "2. 색상, 사이즈 정보로 옵션 아이디 가젹오기<br/>"
                    + "3. 해당 값들로 장바구니에 insert<br/>"
                    + "- request param : productId (상품 ID)<br/>"
                    + "- request param : color (색상)<br/>"
                    + "- request param : size (사이즈)<br/>"
                    + "- request param : quantity (수량)"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "상품 ID", required = true, dataType = "String", paramType = "query", example = "1001"),
            @ApiImplicitParam(name = "color", value = "상품 색상", required = true, dataType = "String", paramType = "query", example = "Black"),
            @ApiImplicitParam(name = "size", value = "상품 사이즈", required = true, dataType = "String", paramType = "query", example = "M"),
            @ApiImplicitParam(name = "quantity", value = "수량", required = true, dataType = "int", paramType = "query", example = "2")
    })
    @PostMapping("/prdDetlCartUpdate")
    public String addToCart(
            HttpSession session,
            @RequestParam("productId") String productId,
            @RequestParam("color") String color,
            @RequestParam("size") String size,
            @RequestParam("quantity") int quantity,
            Model m
    ) {
        int userId = (Integer) session.getAttribute("userId");
        m.addAttribute("userId",userId);

        ProductDetailDTO detailDTO = new ProductDetailDTO();
        ProductVo productVo = new ProductVo();
        CartRequestDTO cartRequestDTO = new CartRequestDTO();

        // 입력 값 정제
        String sanitizedColor = sanitize(color);
        String sanitizedSize = sanitize(size);
        int sanitizedQuantity = Math.max(1, quantity);
        int basePrice = productVo.getPrice();
        int additionalPrice = productVo.getPrice();
        int totalPrice = (basePrice + additionalPrice) * sanitizedQuantity;

        String productOptionId = productService.getProductOptionId(productId, sanitizedColor, sanitizedSize);

        cartRequestDTO.setUserId(userId);
        cartRequestDTO.setProductOptionId(Integer.parseInt(productOptionId));
        cartRequestDTO.setProductCount(sanitizedQuantity);

        cartInsertService.goInsert(cartRequestDTO);
        System.out.println(cartRequestDTO);
        return "redirect:/cart";
    }


    @ApiOperation(
            value = "상품 주문 요청 처리",
            notes = "설명 : 상품의 옵션 및 수량을 받아 주문으로 전송.<br/><br/>"
                    + "API 사용 목록<br/>"
                    + "1. 상품 ID, 색상, 사이즈, 수량을 받아온다.<br/>"
                    + "2. 색상, 사이즈 정보로 옵션 아이디 가젹오기<br/>"
                    + "3. 주문으로 리다이렉트<br/>"
                    + "- request param : productId (상품 ID)<br/>"
                    + "- request param : color (색상)<br/>"
                    + "- request param : size (사이즈)<br/>"
                    + "- request param : quantity (주문 수량)"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "상품 ID", required = true, dataType = "String", paramType = "query", example = "1001"),
            @ApiImplicitParam(name = "color", value = "상품 색상", required = true, dataType = "String", paramType = "query", example = "Black"),
            @ApiImplicitParam(name = "size", value = "상품 사이즈", required = true, dataType = "String", paramType = "query", example = "M"),
            @ApiImplicitParam(name = "quantity", value = "주문 수량", required = true, dataType = "int", paramType = "query", example = "2")
    })
    @PostMapping("/order1")
    public String addToOrder1(
            @RequestParam("productId") String productId,
            @RequestParam("color") String color,
            @RequestParam("size") String size,
            @RequestParam("quantity") int quantity,
            HttpSession session,
            Model m
    ){
        int userId = (Integer) session.getAttribute("userId");
        m.addAttribute("userId",userId);

        String sanitizedColor = sanitize(color);
        String sanitizedSize = sanitize(size);
        int sanitizedQuantity = Math.max(1, quantity);


        String productOptionId = productService.getProductOptionId(productId, sanitizedColor, sanitizedSize);

        String items = productOptionId + ":" + sanitizedQuantity;

        System.out.println("[AddToOrder] " + items);
        m.addAttribute("userId",userId);
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
