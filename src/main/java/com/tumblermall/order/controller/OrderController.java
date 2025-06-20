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

    @GetMapping("/checkout")
    public String checkout(HttpServletRequest request, Model model) {
        String id = request.getParameter("userId");
        //제품 하드코딩
        List<Integer> itemlist = new ArrayList<>();
        itemlist.add(1);
        itemlist.add(3);
        itemlist.add(5);
        try {
            String userIdVal = request.getParameter("userId");
            UserInfoRequestDTO userInfo = new UserInfoRequestDTO();
            String userId = userInfo.setName(userIdVal);

            if (userId == null) {
                throw new Exception("userId is Null");
            }

            String userName = userService.userNameTest(userId);

            List<ProductVo> pv = orderService.selectProduct(itemlist);
            List<AdressVo> av = orderService.selectAdress(id);
            model.addAttribute("userName", userName);
            model.addAttribute("addressList", av);
            model.addAttribute("pv", pv);
            return "order/orderPayment";
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return "/common/error";
        }
    }

    @GetMapping("/address")
    public String address(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        String id = request.getParameter("userId");
        List<AdressVo> av = orderService.selectAdress(id);
        System.out.println(av);
        model.addAttribute("addressList", av);
        return "order/address";
    }
}
