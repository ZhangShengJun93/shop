package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.SkuProperty;
import cn.wolfcode.shop.domain.SkuPropertyValue;

import java.util.List;

public interface ISkuPropertyService {
    List<SkuProperty> getSkuPropertyByCatalogId(Long catalogId);

    SkuProperty getSkuProperty(Long skuPropertyId);


    void update(SkuProperty skuPropertyDb);

    void insert(SkuProperty skuProperty);

    void delete(Long id);

    List<SkuPropertyValue> getValueByskuPropertyId(Long skuPropertyId);

    void insertPropertyValue(SkuPropertyValue skuPropertyValue);

    void deleteValueById(Long skuPropetryValueId);
}
