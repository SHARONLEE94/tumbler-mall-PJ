package com.tumblermall.order.mapper;

import com.tumblermall.order.vo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<AdressVo> selectAdress(int id);

    ProductVo selectProduct(int productOptionId);
}
