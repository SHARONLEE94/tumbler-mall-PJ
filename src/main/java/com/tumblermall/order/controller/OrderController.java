package com.tumblermall.order.controller;

import com.tumblermall.order.service.OrderService;
import com.tumblermall.order.vo.AdressVo;
import com.tumblermall.order.vo.ProductVo;
import com.tumblermall.order.vo.userInfoVo;
import com.tumblermall.products.service.ProductService;
import com.tumblermall.user.dto.UserInfoRequestDTO;
import com.tumblermall.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    //테스트
//    @GetMapping("/")
//    public String a(Model model, HttpServletRequest request) {
//        //임시
//        model.addAttribute("items","1:1");
//        return "redirect:/orderTest";
//    }
    //주문/결제페이지
    @GetMapping("/orderCart")
    public String orderCart(HttpServletRequest request, @RequestParam("items") List<String> items, Model model) {
        String id = request.getParameter("userId");


        try {
            String userIdVal = request.getParameter("userId");

            if (userIdVal == null) {
                throw new Exception("userId is Null");
            }

            userInfoVo userInfo = orderService.userInfo(userIdVal);
            List<ProductVo> pv = orderService.selectProduct(items);
            AdressVo av = orderService.selectAdressDefault(id);
            model.addAttribute("userInfo", userInfo);
            model.addAttribute("address", av);
            model.addAttribute("productList", pv);
            return "/order/order";
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return "/common/error";
        }
    }
    //주소 변경/생성 페이지
    @GetMapping("/address/edit")
    public String addressAdit(HttpServletRequest request, Model model) {
        //임시
        List<AdressVo> av = orderService.selectAddress("1");
//        System.out.println(av);
        model.addAttribute("addressList", av);
        return "order/addressEdit";
    }
    //주소 삭제
    @GetMapping("/address/del")
    public String delAddress(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        String id  = request.getParameter("addressBookId");
        try {
            orderService.delAddress(id);
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return "/common/error";
        }
        //임시
        model.addAttribute("userId",1);
        return "redirect:/orderCart";
    }
    //주문!
    @PostMapping("/order")
    public String order(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        try {
            orderService.orderInsert(request);
        }catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return "/common/error";}
        return "redirect:/main";
    }


    @GetMapping("/orderTest")
    public String address(HttpServletRequest request, @RequestParam("items") List<String> items, Model model) throws IOException {
        List<ProductVo> pv = orderService.selectProduct(items);
        model.addAttribute("productList", pv);
        return "order/address";
    }

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
