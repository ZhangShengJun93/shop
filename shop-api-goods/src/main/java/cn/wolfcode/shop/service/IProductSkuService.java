package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.ProductSku;
import cn.wolfcode.shop.viewObject.ProductVo;
import cn.wolfcode.shop.viewObject.SkuGenerateFormVo;

import java.util.List;
import java.util.Map;

public interface IProductSkuService {
    List<ProductSku> getSkuByProductId(Long productId);

    List<Map<String,Object>> generateSku(SkuGenerateFormVo vo);

    void save(ProductVo vo);
    
}
