package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.constants.Constants;
import cn.wolfcode.shop.domain.OrderAction;
import cn.wolfcode.shop.domain.OrderInfo;
import cn.wolfcode.shop.domain.User;
import cn.wolfcode.shop.mapper.OrderInfoMapper;
import cn.wolfcode.shop.query.OrderInfoQueryObject;
import cn.wolfcode.shop.service.IOrderActionService;
import cn.wolfcode.shop.service.IOrderInfoService;
import cn.wolfcode.shop.service.IUserService;
import cn.wolfcode.shop.util.PageResult;
import cn.wolfcode.shop.vo.OrderAndUserVo;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderInfoServiceImpl implements IOrderInfoService {
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	@Reference
	private IUserService userService;
	@Autowired
	private IOrderActionService orderActionService;

	@Override
	public PageResult query(OrderInfoQueryObject qo) {
		try {
			int count = orderInfoMapper.queryForCount(qo);
			if (count == 0) {
				return PageResult.empty(qo.getPageSize());
			}
			List<OrderInfo> list = orderInfoMapper.queryForList(qo);
			return new PageResult(list, count, qo.getCurrentPage(), qo.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public OrderInfo selectByPrimaryKey(Long id) {
		try {
			OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(id);
			return orderInfo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void userItemSubmit(OrderAndUserVo vo) {
		try {
			if (vo != null) {
				OrderInfo oi = vo.getOrderInfo();
				User u = vo.getUser();
				OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(vo.getOrderInfo().getId());
				orderInfo.setAddress(oi.getAddress());
				orderInfo.setMobile(oi.getMobile());
				User user = orderInfo.getUser();
				user.setUsername(u.getUsername());
				user.setEmail(u.getEmail());
				user.setExperience(u.getExperience());
				user.setPhone(u.getPhone());
				orderInfoMapper.updateByPrimaryKey(orderInfo);
				userService.update(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void changeOrderStatus(String orderDesc, Byte status, Long orderId) {
		OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderId);
		OrderAction orderAction = new OrderAction();
		orderAction.setActionNote(orderDesc);
		orderAction.setActionPlace(new Byte("1"));
		orderAction.setActionTime(new Date());
		orderAction.setActionUser("刘子敬");
		orderAction.setOrderStatus(orderInfo.getOrderStatus());
		orderAction.setShippingStatus(orderInfo.getShippingStatus());
		orderAction.setPayStatus(orderInfo.getPayStatus());
		orderAction.setOrderId(orderId);
		orderActionService.insert(orderAction);
	}
	@Override
	public void update(BigDecimal totalAmout, Long id) {
		OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(id);
		orderInfo.setOrderAmount(totalAmout);
		orderInfoMapper.updateByPrimaryKey(orderInfo);
	}

	@Override
	public List<OrderInfo> selectAllSend() {
		return orderInfoMapper.selectAllSend();
	}

	@Override
	public void updateState(Long id) {
		OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(id);
		orderInfo.setShippingStatus(Constants.ORDER_SHIPPING_COMPLETE);
		orderInfoMapper.updateByPrimaryKey(orderInfo);
	}
}
