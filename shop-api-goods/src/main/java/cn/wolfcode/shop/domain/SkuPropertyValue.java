package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkuPropertyValue extends BaseDomain {

    private SkuProperty skuProperty; //sku属性id

    private String value;//属性值

    private Integer sequence;//排序

}