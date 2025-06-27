package com.tumblermall.order.controller;

import com.tumblermall.order.dto.*;
import com.tumblermall.order.service.OrderService;
import com.tumblermall.order.vo.*;
import com.tumblermall.user.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    //주문/결제페이지
    @ApiOperation(
            value = "주문/결제 페이지 API",
            notes = "장바구니에서 가져온 제품을 주문하는 API입니다.<br/><br/>"
            +"?itmes= 1:1 -> 1번 개별제품의 수량 1개<br/>"
            +" 쿼리 스트링 없을땐 user_cart에 정보를 가져옵니다.<br/>"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "items", value = "장바구니 제품",required = false,dataType = "String",example = "1:1")
    })
    @GetMapping("/order")
    public String orderCart(HttpServletRequest request, HttpSession session, @RequestParam(value = "items", required = false) List<String> items, Model model) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        int uesrId = (Integer) session.getAttribute("userId");
        try {
            userInfoVo userInfo = orderService.userInfo(uesrId);
            List<ProductVo> pv = null;
            if (items != null) {
                pv = orderService.selectProduct(items);
            } else {
                pv = orderService.cartSelect(uesrId);
                if (pv.size() ==  0) {
                    return "redirect:/main";
                }
            }
            AdressVo av = orderService.selectAdressDefault(uesrId);
            model.addAttribute("userInfo", userInfo);
            model.addAttribute("address", av);
            model.addAttribute("productList", pv);
            return "/order/order";
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            model.addAttribute("error", e.getMessage());
            return "/common/error";
        }
    }
    @ApiOperation(
            value = "주문+주문 완료 화면 API",
            notes = "주문 완료 화면으로가는 API"
                    +"주문/결제 화면에서 받은 데이터들을 주문 테이블에 Insert시킵니다.<br/>"
                    +"Insert가 완료되면 주문 완료 화면에 필요한 정보를 가져옵니다.<br/>"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name        = "productId",
                    value       = "제품 ID ",
                    required    = true,
                    dataType    = "List",
                    paramType   = "query",
                    example     = "1"
            ),
            @ApiImplicitParam(
                    name        = "quantity",
                    value       = "각 제품별 수량 ",
                    required    = true,
                    dataType    = "List",
                    paramType   = "query",
                    example     = "2"
            ),
            @ApiImplicitParam(
                    name        = "price",
                    value       = "각 제품별 가격 ",
                    required    = true,
                    dataType    = "List",
                    paramType   = "query",
                    example     = "15000"
            ),
            @ApiImplicitParam(
                    name        = "orderDeliveryDto",
                    value       = "배송 정보 DTO",
                    required    = true,
                    dataType    = "OrderDeliveryDto",
                    paramType   = "query"
            ),
            @ApiImplicitParam(
                    name        = "userInfo",
                    value       = "사용자 정보 VO",
                    required    = true,
                    dataType    = "userInfoVo",
                    paramType   = "query"
            ),
            @ApiImplicitParam(
                    name        = "orderDto",
                    value       = "주문 정보 DTO",
                    required    = true,
                    dataType    = "OrderDto",
                    paramType   = "query"
            )
    })
    @PostMapping("/orderProcess")
    public String orderProcess(HttpServletRequest request,
                               @RequestParam("productId") List<Integer> productIds,
                               @RequestParam("quantity") List<Integer> quantities,
                               @RequestParam("price") List<Integer> prices,
                               OrderDeliveryDto orderDeliveryDto,
                               userInfoVo  userInfo,
                               OrderDto orderDto,
                               HttpServletResponse response, Model model) throws IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        int userId = (Integer) session.getAttribute("userId");
        String payMethod = "";
        String paymentMethodDisplay;
        List<OrderDetailVo> orderDetailVoList = new ArrayList<>();
        try {
            orderService.orderInsert(userId, orderDto, productIds, quantities, prices, orderDeliveryDto);
            orderDetailVoList = orderService.oderDetailSelect(orderDto.getOrderId());
            payMethod = orderDto.getPaymentMethod();

        } catch (IllegalArgumentException e) {
            //DB오류
            e.printStackTrace();
            e.getMessage();
            return "/common/error";
        } catch (Exception e) {
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
        model.addAttribute("orderDto", orderDto);
        model.addAttribute("deliveryRequest", orderDeliveryDto.getDeliveryRequest());
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("discount", discount);
        model.addAttribute("paymentMethod", paymentMethodDisplay);
        model.addAttribute("orderDeliveryDto", orderDeliveryDto);
        model.addAttribute("orderDetailDtoList", orderDetailVoList);
        return "/order/orderProcess";
    }

    @ApiOperation(
            value = "주문 상세 페이지 API입니다.",
            notes = "주문 상세를 보여줍니다."
                    +"<br/>"
                   + "주문ID를 가지고 주문 상세 정보를 가져옵니다."
                    +"<br/>"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderNumber", value = "주문ID",required = true,dataType = "int",example = "1061")
    })
    @GetMapping("/orderDetl")
    public String orderDetl(HttpServletRequest request, @RequestParam("orderNumber") int orderId, Model model) throws IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        int userId = (Integer) session.getAttribute("userId");
        if (orderService.orderUserSelect(userId, orderId) == 0) {
//            throw new RuntimeException("당사자가아님");
            return "redirect:/main";
        }
        OrderVo orderVo = orderService.orderSelect(orderId);
        OrderDeliveryDto adressVo = orderService.orderAddressSelect(orderId);
        List<OrderDetailVo> orderDetailVoList = orderService.oderDetailSelect(orderId);
        model.addAttribute("order", orderVo);
        model.addAttribute("orderAdress", adressVo);
        model.addAttribute("orderDetailList", orderDetailVoList);
        return "/order/orderDetl";
    }
    @ApiOperation(
            value = "주문 목록 페이지 API",
            notes = "완료된 주문 목록을 보여주는 페이지입니다.<br/>"
                    +"userId기준으로 주문이 완료된 데이터를 가져옵니다. <br/>"
    )
    @ApiImplicitParams({
    })
    @GetMapping("/orderList")
    public String orderList(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        int userId = 0;
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        try {
            userId = (Integer) session.getAttribute("userId");
        } catch (Exception e) {
            return "redirect:/login";
        }

        List<OrderVo> orderList = orderService.orderListSelect(userId);

        model.addAttribute("orderList", orderList);
        return "/order/orderList";
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
        String id = request.getParameter("addressBookId");
        try {
            orderService.delAddress(id);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return "/common/error";
        }
        //임시
        model.addAttribute("userId", 1);
        return "redirect:/orderCart";
    }
}
