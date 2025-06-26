package com.tumblermall.order.mapper;

import com.tumblermall.order.dto.*;
import com.tumblermall.order.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    //select
    List<AdressVo> selectAdress(int id);
    AdressVo selectAdressDefault(int id);
    ProductVo selectProduct(int productOptionId);
    //insert
    boolean insertOrderDetail(@Param("list") List<OrderDetailDto> list);
    boolean insertOrder(OrderDto orderDto);
    boolean insertOrderAdress(OrderDeliveryDto orderDeliveryDto);
    //update

    // delete
    boolean delAddress(int id);

    userInfoVo orderUserInfo(int userId);

    List<OrderDetailVo> oderDetailSelect(int orderId);

    OrderVo orderSelect(int orderId);

    OrderDeliveryDto orderAddressSelect(int orderId);

    int orderUserSelect(@Param("userId") int userId, @Param("orderId") int orderId);

    List<OrderVo> orderListSelect(int userId);

    List<ProductVo> cartSelect(int userId);
}
