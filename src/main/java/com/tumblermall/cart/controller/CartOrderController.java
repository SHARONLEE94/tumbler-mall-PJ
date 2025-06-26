package com.tumblermall.cart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartOrderController {

    @PostMapping("/cartOrder")
    public String goOrder(@RequestParam("productOptionId") String[] productOptionId,
                          @RequestParam("productCount") String[] counts,
                          Model model){
        try {
            List<Map<String, Integer>> orderList = new ArrayList<>();

            for (int i = 0; i < productOptionId.length; i++) {
                Map<String, Integer> item = new HashMap<>();
                item.put("productOptionId", Integer.parseInt(productOptionId[i]));
                item.put("productCount", Integer.parseInt(counts[i]));
                orderList.add(item);
            }

            model.addAttribute("orderList", orderList);
        }

        catch (Exception e){
            e.printStackTrace();
        }

        return "/order/order";
    }
}
