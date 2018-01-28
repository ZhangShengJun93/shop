package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.constants.Constants;
import cn.wolfcode.shop.domain.OrderInfo;
import cn.wolfcode.shop.domain.OrderReturn;
import cn.wolfcode.shop.mapper.OrderInfoMapper;
import cn.wolfcode.shop.mapper.OrderReturnMapper;
import cn.wolfcode.shop.query.QueryObject;
import cn.wolfcode.shop.service.IOrderInfoService;
import cn.wolfcode.shop.service.IOrderReturnService;
import cn.wolfcode.shop.util.PageResult;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class OrderReturnServiceImpl implements IOrderReturnService{
	@Autowired
	private OrderReturnMapper orderReturnMapper;
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	@Autowired
	private IOrderInfoService orderInfoService;
	@Override
	public void insert(Long orderId, String note) {
		
		OrderInfo orderInfo = orderInfoService.selectByPrimaryKey(orderId);
		OrderReturn orderReturn = new OrderReturn();
		orderReturn.setAmount(orderInfo.getOrderAmount());
		orderReturn.setNote(note);
		orderReturn.setReturnTime(new Date());
		orderReturn.setUsername(orderInfo.getUser().getUsername());
		orderReturnMapper.insert(orderReturn);
	}
	@Override
	public void auditor(Long id) {
		OrderReturn orderReturn = orderReturnMapper.selectByPrimaryKey(id);
		orderReturn.setState(Constants.ORDER_RETURN_OK);
		orderReturnMapper.updateByPrimaryKey(orderReturn);
	}


	@Override
	public PageResult query(QueryObject qo) {
		int count = orderReturnMapper.queryForCount(qo);
		if (count <= 0){
		    return PageResult.empty(qo.getPageSize());
		}
		return new PageResult(orderReturnMapper.queryForList(qo),
		count,qo.getCurrentPage(),qo.getPageSize());
	}
}
