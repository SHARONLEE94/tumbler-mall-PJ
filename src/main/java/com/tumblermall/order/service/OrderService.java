package com.tumblermall.order.service;

import com.tumblermall.order.mapper.OrderMapper;
import com.tumblermall.order.vo.AdressVo;
import com.tumblermall.order.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    public List<AdressVo> selectAdress(String i) {
        int id = Integer.parseInt(i);
        return orderMapper.selectAdress(id);
    }

    public List<ProductVo> selectProduct(List<Integer> itemlist) {
        List<ProductVo> products = new ArrayList<>();
        for (int id:  itemlist) {
            ProductVo p = orderMapper.selectProduct(id);
            if (p != null) {
                products.add(p);
            }
        }
        System.out.println(products.toString().toString());
        return products;
    }
}
