package cn.wolfcode.shop.mapper;

        import cn.wolfcode.shop.domain.Catalog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CatalogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Catalog record);

    Catalog selectByPrimaryKey(Long id);

    List<Catalog> selectAll();

    int updateByPrimaryKey(Catalog record);

    Catalog getCatalogByParentId(Long parentCatalogId);

    List<Catalog> getChildenCatalogById(@Param("id") Long id);

    List<Catalog> getChildenCatalogByIds(@Param("id") Long id);

    Catalog selectInfParentByparentCatalogId(Long parentCatalogId);
}