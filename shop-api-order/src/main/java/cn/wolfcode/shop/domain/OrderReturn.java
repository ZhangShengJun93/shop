package cn.wolfcode.shop.domain;

import cn.wolfcode.shop.constants.Constants;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
public class OrderReturn extends BaseDomain{
    private BigDecimal amount;

    private Date returnTime;

    private String username;

    private Integer state = Constants.ORDER_RETURN_NO;

    private String note;

    public String getStateDis(){
    	switch (state){
		    case Constants.ORDER_RETURN_NO:return "待审核";
		    case Constants.ORDER_RETURN_OK:return "已审核";
	    }
	    return "未知";
    }
}