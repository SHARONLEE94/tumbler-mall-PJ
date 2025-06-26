package com.tumblermall.order.service;

import com.tumblermall.order.dto.*;
import com.tumblermall.order.mapper.OrderMapper;
import com.tumblermall.order.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    public List<AdressVo> selectAddress(String i) {
        int id = Integer.parseInt(i);
        return orderMapper.selectAdress(id);
    }

    public AdressVo selectAdressDefault(int i) {
        return orderMapper.selectAdressDefault(i);
    }

    public List<ProductVo> selectProduct(List<String> items) {
        List<ProductVo> products = new ArrayList<>();
        Set<Integer> seenIds = new HashSet<>();

        for (String pair : items) {
            // "id:count" 형식으로 넘어온다고 가정
            String[] parts = pair.split(":");
            // 유효성 검사: parts 길이 확인
            if (parts.length != 2) {
                throw new IllegalArgumentException("잘못된 항목 포맷: " + pair + " (올바른 형식은 '상품ID:수량' 형태여야 합니다.)");
            }

            int productId;
            int count;
            try {
                productId = Integer.valueOf(parts[0].trim());
                count = Integer.valueOf(parts[1].trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("상품 ID나 수량이 숫자가 아닙니다: " + pair, e);
            }

            // 중복 검사: 이미 처리된 ID가 있으면 예외
            if (!seenIds.add(productId)) {
                // 중복 발견 시 원하는 예외로 던집니다. RuntimeException 계열이면 트랜잭션 롤백에도 반영됨.
                throw new IllegalArgumentException("중복된 상품 ID가 발견되었습니다: " + productId);
            }

            // ID가 중복이 아니면 select 후 추가 처리
            ProductVo p = orderMapper.selectProduct(productId);
            if (p == null) {
                throw new IllegalStateException("해당 상품을 찾을 수 없습니다. 상품 ID: " + productId);
            }
            p.setQuantity(count);
            products.add(p);

            // 추가 처리 로직이 있다면 여기에...
        }
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

    @Transactional(rollbackFor = Exception.class)
    public void orderInsert(int userId, OrderDto orderDto, List<Integer> productIds, List<Integer> quantities, List<Integer> prices, OrderDeliveryDto orderDeliveryDto) throws Exception {
        orderDto.setUserId(userId);
        boolean insertOrder = orderMapper.insertOrder(orderDto);
        if (!insertOrder) {
            throw new IllegalArgumentException("order insert failed.");
        }
        int orderId = orderDto.getOrderId();
        orderDeliveryDto.setOrderId(orderId);
        boolean insertAddress = orderMapper.insertOrderAdress(orderDeliveryDto);
        if (!insertAddress) {
            throw new IllegalArgumentException("orderAddress insert failed.");
        }
        int n = productIds.size();
        if (quantities.size() != n || prices.size() != n) {
            throw new IllegalArgumentException("상품 정보 개수가 일치하지 않습니다.");
        }

        List<OrderDetailDto> batchList = new ArrayList<>();
        int count = 1;
        for (int i = 0; i < n; i++) {
            int productOptionId = productIds.get(i);
            int price           = prices.get(i);
            for (int j =0 ; j <quantities.get(i); j++) {
                OrderDetailDto dto = new OrderDetailDto();
                dto.setOrderId(orderId);
                dto.setProductId(productOptionId);
                dto.setPrice(price);
                dto.setSeq(count++);
                batchList.add(dto);
            }
//            detailDto.setQuantity(quantities.get(i));
        }
        boolean orderDetail = orderMapper.insertOrderDetail(batchList);
        if (!orderDetail) {
            throw new IllegalArgumentException("orderDetail insert failed.");
        }
    }

    public userInfoVo userInfo(int userIdVal) {
        return orderMapper.orderUserInfo(userIdVal);
    }

    public List<OrderDetailVo> oderDetailSelect(int orderId) {

        return orderMapper.oderDetailSelect(orderId);
    }

    public OrderVo orderSelect(int orderId) {

        return orderMapper.orderSelect(orderId);
    }

    public OrderDeliveryDto orderAddressSelect(int orderId) {
        return orderMapper.orderAddressSelect(orderId);
    }

    public int orderUserSelect(int userId,int orderId) {
        return orderMapper.orderUserSelect(userId,orderId);
    }

    public List<OrderVo> orderListSelect(int userId) {
        return orderMapper.orderListSelect(userId);
    }

    public List<ProductVo> cartSelect(int userId) {

        return orderMapper.cartSelect(userId);
    }
}
