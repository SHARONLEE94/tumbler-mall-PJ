package com.tumblermall.order.mapper;

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
    long selectLastInsertId();
    //insert
    boolean insertOrder();
    boolean insertOrderAdress(@Param("id") int id, @Param("request") String request);
    boolean insertOrderdetail();
    //update

    // delete
    boolean delAddress(int id);

    userInfoVo orderUserInfo(int userId);
}
