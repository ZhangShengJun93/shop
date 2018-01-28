package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.OrderAction;

import java.util.List;

public interface OrderActionMapper {
    int insert(OrderAction record);

	List<OrderAction> queryById(Long id);
}