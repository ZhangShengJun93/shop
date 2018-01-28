package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.OrderProduct;
import cn.wolfcode.shop.mapper.OrderProductMapper;
import cn.wolfcode.shop.service.IOrderProductService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OrderProductServiceImpl implements IOrderProductService {

	@Autowired
	private OrderProductMapper orderProductMapper;
	@Override
	public OrderProduct queryOrderProduct(Long id) {
		return orderProductMapper.selectByPrimaryKey(id);
	}
}
