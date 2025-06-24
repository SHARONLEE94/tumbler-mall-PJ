package com.tumblermall.order.controller;

import com.tumblermall.order.dto.OrderDeliveryDto;
import com.tumblermall.order.dto.OrderDetailDto;
import com.tumblermall.order.dto.OrderDto;
import com.tumblermall.order.service.OrderService;
import com.tumblermall.order.vo.AdressVo;
import com.tumblermall.order.vo.ProductVo;
import com.tumblermall.order.vo.userInfoVo;
import com.tumblermall.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
//        model.addAttribute("userId",1);
//        model.addAttribute("items","1:1");
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
    //주문!
    @PostMapping("/order")
    public String order(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        try {
//            orderService.orderInsert(request);
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


        orderService.orderInsert(userId,orderDto,productIds,quantities,prices,orderDeliveryDto);
        model.addAttribute("userId",1);
        model.addAttribute("orderId",orderDto.getOrderId());
        model.addAttribute("userName",request.getParameter("orderName"));
        model.addAttribute("orderPhone",request.getParameter("orderPhone"));
        model.addAttribute("orderEmail",request.getParameter("orderEmail"));
        model.addAttribute("totalPrice",orderDto.getTotalPrice());
        model.addAttribute("orderDeliveryDto",orderDeliveryDto);
        return"/order/orderProcess";
    }

    @GetMapping("/orderProcess")
    public String orderProcess(HttpServletRequest request) {
        String address = request.getParameter("address");
        System.out.println(address);
        return "/order/orderProcess";
    }

    @GetMapping("/orderDetl")
    public String orderDetl() {
        return "/order/orderDetl";
    }
}
