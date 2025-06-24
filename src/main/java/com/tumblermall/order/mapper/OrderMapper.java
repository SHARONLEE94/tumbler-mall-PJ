package com.tumblermall.order.mapper;

import com.tumblermall.order.dto.*;
import com.tumblermall.order.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    //select
    List<AdressVo> selectAdress(int id);
    AdressVo selectAdressDefault(int id);
    ProductVo selectProduct(int productOptionId);
    //insert
    boolean insertOrderDetail(OrderDetailDto orderProductDto);
    int insertOrder(OrderDto orderDto);
    boolean insertOrderAdress(OrderDeliveryDto orderDeliveryDto);
    //update

    // delete
    boolean delAddress(int id);

    userInfoVo orderUserInfo(int userId);
}
