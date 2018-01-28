package cn.wolfcode.shop.service;

import cn.wolfcode.shop.query.QueryObject;
import cn.wolfcode.shop.util.PageResult;

/**
 * 退货订单的添加与审核
 */
public interface IOrderReturnService {
	void insert(Long  orderId,String note);

	void auditor(Long id);

	PageResult query(QueryObject qo);
}
