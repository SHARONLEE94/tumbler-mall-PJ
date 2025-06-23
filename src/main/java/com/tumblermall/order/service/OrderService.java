package com.tumblermall.order.service;

import com.tumblermall.order.mapper.OrderMapper;
import com.tumblermall.order.vo.AdressVo;
import com.tumblermall.order.vo.ProductVo;
import com.tumblermall.order.vo.userInfoVo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    public List<AdressVo> selectAddress(String i) {
        int id = Integer.parseInt(i);
        return orderMapper.selectAdress(id);
    }

    public AdressVo selectAdressDefault(String i) {
        int id = Integer.parseInt(i);
        return orderMapper.selectAdressDefault(id);
    }

    public List<ProductVo> selectProduct(List<String> items) {
        //제품 하드코딩
        List<ProductVo> products = new ArrayList<>();
        for (String pair : items) {
            String[] parts = pair.split(":");
            Integer count = Integer.valueOf(parts[1]);
            ProductVo p = orderMapper.selectProduct(Integer.parseInt(parts[0]));
            p.setCount(count);
            if (p != null) {
                products.add(p);
            }
            // 처리 로직...
        }
        System.out.println(products.toString().toString());
        return products;
    }

    public boolean delAddress(String id) throws Exception {
        int addressId = Integer.parseInt(id);
        boolean result = orderMapper.delAddress(addressId);
        if (!result) {
            throw new Exception("삭제할수없습니다.");
        }
        return result;
    }

    @Transactional
    public void orderInsert(HttpServletRequest request) {
        List<Integer> itemlist = new ArrayList<>();
        itemlist.add(1);
        itemlist.add(3);
        itemlist.add(5);
        orderMapper.insertOrderAdress(1, "부재 시 경비실에 맡겨주세요");
        long a = orderMapper.selectLastInsertId();
        System.out.printf("a=%d\n", a);
        //        orderMapper.insertOrder();
//        orderMapper.insertOrderdetail();

    }

    public userInfoVo userInfo(String userIdVal) {
        return orderMapper.orderUserInfo(Integer.parseInt(userIdVal));
    }
}
