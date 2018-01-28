package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.CatalogPropertyValue;
import java.util.List;

public interface CatalogPropertyValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CatalogPropertyValue record);

    CatalogPropertyValue selectByPrimaryKey(Long id);

    List<CatalogPropertyValue> selectAll();

    int updateByPrimaryKey(CatalogPropertyValue record);

    List<CatalogPropertyValue> selectCatalogPropertyValueByCatalogPropertyId(Long catalogPropertyId);

    CatalogPropertyValue selectByCatalogPropertyId(Long catalogPropertyId);
}