package com.tumblermall.order.controller;

import com.tumblermall.order.dto.*;
import com.tumblermall.order.service.OrderService;
import com.tumblermall.order.vo.*;
import com.tumblermall.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
//        model.addAttribute("userId",1);
//        model.addAttribute("items","5:3");
//        return "redirect:/order";
//    }
    //주문/결제페이지
    @GetMapping("/order")
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
            model.addAttribute("error",e.getMessage());
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

    @GetMapping("/orderTest")
    public String address(HttpServletRequest request, @RequestParam("items") List<String> items, Model model) throws IOException {
        List<ProductVo> pv = orderService.selectProduct(items);
        model.addAttribute("productList", pv);
        return "order/address";
    }

    @PostMapping("/orderProcess")
    public String orderProcess(HttpServletRequest request,
                               @RequestParam("productId") List<Integer> productIds,
                               @RequestParam("quantity")  List<Integer> quantities,
                               @RequestParam("price")     List<Integer> prices,
                               OrderDeliveryDto orderDeliveryDto,
                               OrderDto orderDto,
                               HttpServletResponse response, Model model) throws IOException {
       //임시
        int userId = 1;
        String payMethod = "";
        String paymentMethodDisplay;
        List<OrderDetailVo>  orderDetailVoList = new ArrayList<>();
        try {
            orderService.orderInsert(userId,orderDto,productIds,quantities,prices,orderDeliveryDto);
            orderDetailVoList = orderService.oderDetailSelect(orderDto.getOrderId());
            payMethod = orderDto.getPaymentMethod();

        }
        catch (IllegalArgumentException e){
            //DB오류
            e.printStackTrace();
            e.getMessage();
            return "/common/error";
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return "/common/error";
        }
        switch (payMethod) {
            case "card":
                paymentMethodDisplay = "신용카드";
                break;
            case "bank":
                paymentMethodDisplay = "계좌이체";
                break;
            case "kakao":
                paymentMethodDisplay = "카카오페이";
                break;
            case "naver":
                paymentMethodDisplay = "네이버페이";
                break;
            default:
                paymentMethodDisplay = "알 수 없음";
        }
        int discount = orderDto.getDiscount() + orderDto.getPointUse();
        model.addAttribute("userId",userId);
        model.addAttribute("orderDto",orderDto);
        model.addAttribute("orderDate",orderDetailVoList);
        model.addAttribute("deliveryRequest",orderDeliveryDto.getDeliveryRequest());
        model.addAttribute("userName",request.getParameter("orderName"));
        model.addAttribute("orderPhone",request.getParameter("orderPhone"));
        model.addAttribute("orderEmail",request.getParameter("orderEmail"));
        model.addAttribute("discount",discount);
        model.addAttribute("paymentMethod",paymentMethodDisplay);
        model.addAttribute("orderDeliveryDto",orderDeliveryDto);
        model.addAttribute("orderDetailDtoList",orderDetailVoList);
        return"/order/orderProcess";
    }

    @GetMapping("/orderProcess")
    public String orderProcess(HttpServletRequest request) {
        String address = request.getParameter("address");
        System.out.println(address);
        return "/order/orderProcess";
    }

    @GetMapping("/orderDetl")
    public String orderDetl(HttpServletRequest request,@RequestParam("orderNumber") int orderId ,Model model) throws IOException {
        OrderVo orderVo =  orderService.orderSelect(orderId);
        Date date = orderVo.getOrderDate();

        OrderDeliveryDto adressVo = orderService.orderAddressSelect(orderId);
        List<OrderDetailVo> orderDetailVoList = orderService.oderDetailSelect(orderId);
        model.addAttribute("order",orderVo);
        model.addAttribute("orderAdress",adressVo);
        model.addAttribute("orderDetailList",orderDetailVoList);
        return "/order/orderDetl";
    }
}
