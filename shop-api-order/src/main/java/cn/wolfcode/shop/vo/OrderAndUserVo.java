package cn.wolfcode.shop.vo;

import cn.wolfcode.shop.domain.OrderInfo;
import cn.wolfcode.shop.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OrderAndUserVo implements Serializable{
	private User user;
	private OrderInfo orderInfo;
}
