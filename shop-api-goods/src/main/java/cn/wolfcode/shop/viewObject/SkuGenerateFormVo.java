package cn.wolfcode.shop.viewObject;

import cn.wolfcode.shop.domain.SkuProperty;
import cn.wolfcode.shop.domain.SkuPropertyValue;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class SkuGenerateFormVo implements Serializable {

    private Long productId;//商品id

    private List<SkuProperty> SkuPropertyList; //sku属性集合
    
    private List<SkuPropertyValue> SkuPropertyValueList; //sku属性值集合
}
