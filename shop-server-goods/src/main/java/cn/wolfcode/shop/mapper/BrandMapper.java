package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.Brand;
import cn.wolfcode.shop.query.BrandQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Brand record);

    Brand selectByPrimaryKey(Long id);

    List<Brand> selectAll();

    int updateByPrimaryKey(Brand record);

    int queryForCount(BrandQueryObject qo);

    List queryForList(BrandQueryObject qo);

    void insertBrandCatalogRelation(@Param("BrandId") Long BrandId, @Param("catalogId") Long catalogId);

    void deleteBrandCatalogRelation(Long id);
}