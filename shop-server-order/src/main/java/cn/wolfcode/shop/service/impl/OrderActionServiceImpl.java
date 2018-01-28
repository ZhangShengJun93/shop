package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.OrderAction;
import cn.wolfcode.shop.mapper.OrderActionMapper;
import cn.wolfcode.shop.service.IOrderActionService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class OrderActionServiceImpl implements IOrderActionService{
	@Autowired
	private OrderActionMapper orderActionMapper;
	@Override
	public void insert(OrderAction orderAction) {
		orderActionMapper.insert(orderAction);
	}

	@Override
	public List<OrderAction> queryById(Long id) {
		return orderActionMapper.queryById(id);
	}
}
