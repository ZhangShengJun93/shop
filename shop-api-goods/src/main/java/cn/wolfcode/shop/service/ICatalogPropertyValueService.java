package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.CatalogPropertyValue;

import java.util.List;

/**
 * 分类属性值
 */
public interface ICatalogPropertyValueService {
    /**
     * 根据分类属性id查询分类属性值
     * @param catalogPropertyId
     * @return
     */
    List<CatalogPropertyValue> selectCatalogPropertyValueByCatalogPropertyId(Long catalogPropertyId);

    /**
     * 添加分类属性
     * @param catalogPropertyValue
     * @param values
     */
    int addCatalogPropertyValue(CatalogPropertyValue catalogPropertyValue, String[] values);

    /**
     * 删除属性对应的属性值
     * @param catalogPropertyValueId
     * @return
     */
    int deleteCatalogPropertyValue(Long catalogPropertyValueId);
}
