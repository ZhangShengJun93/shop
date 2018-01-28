package cn.wolfcode.shop.constants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Constants {
	//订单状态  未确认
	public static final byte ORDER_STATUS_UNCONFIRMED = 0;
	//订单状态  已确认
	public static final byte ORDER_STATUS_CONFIRMED = 1;
	//订单状态  已完成
	public static final byte ORDER_STATUS_CANCEL = 2;
	
	
	//物流状态 未确认
	public static final byte ORDER_SHIPPING_UNCONFIRMED = 0;
	//物流状态  已确认
	public static final byte ORDER_SHIPPING_CONFIRMED = 1;
	//物流状态  确认收货
	public static final byte ORDER_SHIPPING_COMPLETE = 2;
	//订单支付状态 未付款
	public static final byte ORDER_PAY_UNCONFIRMED = 0;
	//订单支付状态  已付款
	public static final byte ORDER_PAY_CONFIRMED = 1;
	//退货待审核
	public static final int ORDER_RETURN_NO=0;
	//退货已审核
	public static final int ORDER_RETURN_OK=1;

	
	
}
