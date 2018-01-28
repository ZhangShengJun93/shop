package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.ProductCatalogPropertyValue;
import java.util.List;

public interface ProductCatalogPropertyValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductCatalogPropertyValue record);

    ProductCatalogPropertyValue selectByPrimaryKey(Long id);

    List<ProductCatalogPropertyValue> selectAll();

    int updateByPrimaryKey(ProductCatalogPropertyValue record);


    List<ProductCatalogPropertyValue> getCommonPropertiesByCatalogId(Long catalogId);

    List<ProductCatalogPropertyValue> getAllPropertiesByProductId(Long productId);

    List<ProductCatalogPropertyValue> selectByProductId(Long productId);
}