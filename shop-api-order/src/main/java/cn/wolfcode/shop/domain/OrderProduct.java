package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
public class OrderProduct extends BaseDomain{
    private Long orderId;//商品id

    private Long skuId;//订单的skuid

    private String productName;//商品名称

    private Short productNumber;//购买数量

    private BigDecimal productPrice;//商品价格  sku价格

    private String skuAttr;//sku属性

	private String code;//商品编码 (冗余数据)


}