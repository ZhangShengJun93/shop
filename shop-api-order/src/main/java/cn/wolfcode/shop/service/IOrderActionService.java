package cn.wolfcode.shop.service;


import cn.wolfcode.shop.domain.OrderAction;

import java.util.List;

public interface IOrderActionService {
	void insert(OrderAction orderAction);

	List<OrderAction> queryById(Long id);
}
