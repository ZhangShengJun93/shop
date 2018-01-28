package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.OrderProductPrice;

import java.util.List;

public interface OrderProductPriceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderProductPrice record);

    OrderProductPrice selectByPrimaryKey(Long id);

    List<OrderProductPrice> selectAll();

    int updateByPrimaryKey(OrderProductPrice record);
}