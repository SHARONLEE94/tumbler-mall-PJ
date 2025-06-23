package com.tumblermall.products.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductsController {

    @GetMapping("/productDetl")
    public String index() {
        return "/product/productDetl";
    }
}
