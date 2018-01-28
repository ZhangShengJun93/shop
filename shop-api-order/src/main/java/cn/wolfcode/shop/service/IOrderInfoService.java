package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.OrderInfo;
import cn.wolfcode.shop.query.OrderInfoQueryObject;
import cn.wolfcode.shop.util.PageResult;
import cn.wolfcode.shop.vo.OrderAndUserVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 接口
 */
public interface IOrderInfoService {

	PageResult query(OrderInfoQueryObject qo);

	OrderInfo selectByPrimaryKey(Long id);

	void userItemSubmit(OrderAndUserVo vo);

	void changeOrderStatus(String orderDesc, Byte status, Long orderId);

	void update(BigDecimal totalAmout,Long id);

	List<OrderInfo> selectAllSend();

	void updateState(Long id);

}

