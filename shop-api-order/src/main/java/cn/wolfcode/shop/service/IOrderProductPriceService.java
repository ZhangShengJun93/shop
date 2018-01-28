package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.OrderProductPrice;


public interface IOrderProductPriceService {

	void update(OrderProductPrice orderProductPrice,Long orderId);
}
