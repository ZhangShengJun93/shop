package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.OrderProduct;

/**
 * Created by felix on 2018/01/22.
 */
public interface IOrderProductService {

	OrderProduct queryOrderProduct(Long id);
}
