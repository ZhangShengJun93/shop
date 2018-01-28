package cn.wolfcode.shop.domain;

import cn.wolfcode.shop.constants.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderInfo extends BaseDomain{
    private String orderSn;//订单编号
    private User user;//下单用户id
    private Byte orderStatus = Constants.ORDER_STATUS_UNCONFIRMED;//订单状态  未确认  已确认 已完成
    private Byte shippingStatus=Constants.ORDER_SHIPPING_UNCONFIRMED;//物流状态 未发货 已发货 确认收货
    private Byte payStatus=Constants.ORDER_PAY_UNCONFIRMED;//订单支付状态
    private String consignee;//收货人名称
    private String country;//收货国家
    private String province;//收货省份
    private String city;//收货城市
    private String district;//收货地区
    private String address;//收货地址
    private String zipcode;//邮编
    private String mobile;//收货人电话
    private BigDecimal orderAmount = BigDecimal.ZERO;//订单总价
	private OrderProductPrice orderProductPrice;
	List<OrderAction> orderActions = new ArrayList<OrderAction>();
	@DateTimeFormat
	private Date confirmTime;//下单时间
	public String getAddressInfo(){
		return new StringBuilder().append(city).append(district).append(address)
				.append(consignee).append(":电话"+mobile).toString();
	}
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
			case Constants.ORDER_SHIPPING_CONFIRMED : return "运送中";
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