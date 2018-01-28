package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.OrderReturn;
import cn.wolfcode.shop.query.QueryObject;

import java.util.List;

public interface OrderReturnMapper {

    int insert(OrderReturn record);

    OrderReturn selectByPrimaryKey(Long id);

    List<OrderReturn> selectAll();

    int updateByPrimaryKey(OrderReturn record);

	int queryForCount(QueryObject qo);

	List queryForList(QueryObject qo);
}