package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.OrderProductPrice;
import cn.wolfcode.shop.mapper.OrderProductPriceMapper;
import cn.wolfcode.shop.service.IOrderInfoService;
import cn.wolfcode.shop.service.IOrderProductPriceService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OrderProductPriceServiceImpl implements IOrderProductPriceService{
	@Autowired
	private OrderProductPriceMapper orderProductPriceMapper;
	@Autowired
	private IOrderInfoService orderInfoService;
	@Override
	public void update(OrderProductPrice op,Long orderId) {
		OrderProductPrice orderProductPrice = orderProductPriceMapper.selectByPrimaryKey(op.getId());
		orderProductPrice.setCardPrice(op.getCardPrice());
		orderProductPrice.setDelivery(op.getDelivery());
		orderProductPrice.setDiscount(op.getDiscount());
		orderProductPrice.setInvoice(op.getInvoice());
		orderProductPrice.setProductPrice(op.getProductPrice());
		orderProductPrice.setProtect(op.getProtect());
		orderProductPrice.setTotalAmout();
		orderProductPriceMapper.updateByPrimaryKey(orderProductPrice);
		orderInfoService.update(orderProductPrice.getTotalAmout(),orderId);
	}
}
