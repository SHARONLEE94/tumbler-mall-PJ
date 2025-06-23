package com.tumblermall.order.controller;

import com.tumblermall.order.service.OrderService;
import com.tumblermall.order.vo.AdressVo;
import com.tumblermall.order.vo.ProductVo;
import com.tumblermall.products.service.ProductService;
import com.tumblermall.user.dto.UserInfoRequestDTO;
import com.tumblermall.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    @GetMapping("/")
    public String a(Model model, HttpServletRequest request) {
        //임시
        model.addAttribute("userId",1);
        return "redirect:/orderCart";
    }
    //주문/결제페이지
    @GetMapping("/orderCart")
    public String orderCart(HttpServletRequest request, Model model) {
        String id = request.getParameter("userId");

        try {
            String userIdVal = request.getParameter("userId");
            UserInfoRequestDTO userInfo = new UserInfoRequestDTO();
            String userId = userInfo.setName(userIdVal);

            if (userId == null) {
                throw new Exception("userId is Null");
            }

            String userName = userService.userNameTest(userId);
            //현재하드코딩 되어있음
            List<ProductVo> pv = orderService.selectProduct();
            AdressVo av = orderService.selectAdressDefault(id);
            model.addAttribute("userName", userName);
            model.addAttribute("address", av);
            model.addAttribute("pv", pv);
            return "order/orderPayment";
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


    @GetMapping("/address")
    public String address(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        String id = request.getParameter("userId");
        List<AdressVo> av = orderService.selectAddress(id);
        System.out.println(av);
        model.addAttribute("addressList", av);
        return "order/address";
    }
}
