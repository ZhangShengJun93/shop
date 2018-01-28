package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderProductPrice extends BaseDomain{
    private BigDecimal totalAmout = BigDecimal.ZERO;//总金额

    private BigDecimal discount;//折扣

    private BigDecimal delivery = BigDecimal.ZERO;//配送费

    private BigDecimal protect = BigDecimal.ZERO;//保护费用

    private BigDecimal invoice = BigDecimal.ZERO;//发票

    private BigDecimal cardPrice = BigDecimal.ZERO;//卡片费用

    private BigDecimal paidPrice = BigDecimal.ZERO;//已付费用

	private BigDecimal productPrice = BigDecimal.ZERO;//商品费用

	public void setTotalAmout(){
		this.totalAmout = productPrice.subtract(discount).add(delivery).
				add(protect).add(cardPrice).add(invoice);
	}
	public BigDecimal getHandlePrice(){
		return totalAmout.subtract(paidPrice);
	}
}