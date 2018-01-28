
package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.Catalog;

import java.util.List;
import java.util.Map;
/**
 * 商品分类
 */
public interface ICatalogService {
    /**
     * 商品分类
     * @return
     */
    List<Catalog> getCatalog();

    Catalog getCatalog(Long catalogId);

    Catalog getCatalogByParentId(Long parentCatalogId);

    List<Catalog> selectAll();

    List<Map<String, Object>> getChildenCatalogById(Long id);

    List<Catalog> getChildenCatalogByIds(Long id);

    /**
     * 查询对应id的分类信息
     * @param catalogId
     * @return
     */
    Catalog selectByCatalogId(Long catalogId);

    /**
     * 新建分类
     * @param catalog
     */
    int save(Catalog catalog);

    /**
     * 修改分类
     * @param catalog
     */
    int updata(Catalog catalog);

    /**
     * 删除
     * @param catalogId
     * @return
     */
    int deleteByCatalogId(Long catalogId);
}

