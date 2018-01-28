package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductSku extends BaseDomain {

    private Product product; //商品id

    private String code; //sku编码

    private BigDecimal price;//价格

    private Long mods;

    private List<ProductSkuProperty> productSkuPropertyList;

}