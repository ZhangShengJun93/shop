package cn.wolfcode.shop.viewObject;

import cn.wolfcode.shop.domain.Product;
import cn.wolfcode.shop.domain.ProductSku;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
public class ProductVo implements Serializable {
    
    
    private List<ProductSku> productSkuList;
    
    private Product product;
}
