package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.OrderInfo;
import cn.wolfcode.shop.query.OrderInfoQueryObject;

import java.util.List;

public interface OrderInfoMapper {

    OrderInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKey(OrderInfo record);

	int queryForCount(OrderInfoQueryObject qo);

	List<OrderInfo> queryForList(OrderInfoQueryObject qo);

	List<OrderInfo> selectAllSend();
}