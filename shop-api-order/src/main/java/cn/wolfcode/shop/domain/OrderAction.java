package cn.wolfcode.shop.domain;

import cn.wolfcode.shop.constants.Constants;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderAction extends BaseDomain{
    private Long orderId;//该订单商品所属的订单id

    private String actionUser;//操作用户，有可能是前台用户也有可能是后台管理员

    private Byte orderStatus;//订单状态：0为未确认，1为已确认，2为已完成

    private Byte shippingStatus;//订单物流状态：0为未发货，1为已发货，2为已收货

    private Byte payStatus;//订单支付状态：0为为支付，2为已支付

    private Byte actionPlace;//该操作的发出位置

    private String actionNote;//操作日志

    private Date actionTime;//操作时间
	public String getOrderStatusDis(){
		switch (orderStatus){
			case Constants.ORDER_STATUS_UNCONFIRMED : return "未确认";
			case Constants.ORDER_STATUS_CONFIRMED : return "已确认";
			case Constants.ORDER_STATUS_CANCEL : return "已完成";
		}
		return "未知";
	}
	public String getShippingStatusDis(){
		switch (shippingStatus){
			case Constants.ORDER_SHIPPING_UNCONFIRMED : return "未发货";
			case Constants.ORDER_SHIPPING_CONFIRMED : return "已发货";
			case Constants.ORDER_SHIPPING_COMPLETE : return "已签收";
		}
		return "未知";
	}
	public String getPayStatusDis(){
		switch (payStatus){
			case Constants.ORDER_PAY_UNCONFIRMED : return "未付款";
			case Constants.ORDER_PAY_CONFIRMED : return "已付款";
		}
		return "未知";
	}
}