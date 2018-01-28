package cn.wolfcode.shop.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class OrderInfoQueryObject extends QueryObject{
	private int state = -1 ;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date beginDate ;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate ;
}
