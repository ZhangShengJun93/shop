package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.CatalogProperty;
import java.util.List;

public interface CatalogPropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CatalogProperty record);

    CatalogProperty selectByPrimaryKey(Long id);

    List<CatalogProperty> selectAll();

    int updateByPrimaryKey(CatalogProperty record);

    List<CatalogProperty> selectCatalogPropertyByCatalogId(Long catalogId);
}