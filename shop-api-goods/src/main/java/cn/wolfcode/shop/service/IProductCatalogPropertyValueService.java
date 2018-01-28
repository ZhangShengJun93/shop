package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.ProductCatalogPropertyValue;

import java.util.List;
import java.util.Map;

public interface IProductCatalogPropertyValueService {
    List<Map<String,Object>> getAllPropertiesByProductId(Long productId);


    List<Map<String,Object>> getCommonPropertiesByCatalogId(Long catalogId);

    void insert(List<ProductCatalogPropertyValue> productCatalogPropertyValues, Long productId);

    /**
     *
     * @param productCatalogPropertyValues
     * @param productId
     */
    void update(List<ProductCatalogPropertyValue> productCatalogPropertyValues, Long productId);

}
