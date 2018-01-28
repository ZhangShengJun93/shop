package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.CatalogProperty;

import java.util.List;

/**
 * 分类属性
 */
public interface ICatalogPropertyService {
    /**
     * 查询分类属性
     *
     * @param catalogId
     * @return
     */
    List<CatalogProperty> selectByCatalogId(Long catalogId);

    /**
     * 查询指定分类属性
     *
     * @param catalogPropertyId
     * @return
     */
    CatalogProperty selectCatalogPropertyByCatalogPropertyId(Long catalogPropertyId);

    /**
     * 根据分类属性id查询分类属性
     *
     * @param id
     * @return
     */
    CatalogProperty selectByPrimaryKey(Long id);

    /**
     * 保存分类信息系属性
     *
     * @param catalogProperty
     * @return
     */
    int save(CatalogProperty catalogProperty);

    /**
     * 分类属性信息修改
     *
     * @param catalogProperty
     * @return
     */
    int updata(CatalogProperty catalogProperty);

    /**
     * 删除shuxing
     *
     * @param id
     */
    void delete(Long id);
}
