package com.tumblermall.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    @GetMapping("/order")
    public String order() {
        return "/order/order";
    }

    @GetMapping("/orderProcess")
    public String orderProcess() {
        return "/order/orderProcess";
    }

    @GetMapping("/orderDetl")
    public String orderDetl() {
        return "/order/orderDetl";
    }
}
