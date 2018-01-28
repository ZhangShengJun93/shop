package cn.wolfcode.shop.mapper;

import cn.wolfcode.shop.domain.ProductDesc;
import java.util.List;

public interface ProductDescMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductDesc record);

    ProductDesc selectByPrimaryKey(Long id);

    List<ProductDesc> selectAll();

    int updateByPrimaryKey(ProductDesc record);

    ProductDesc selectByProductId(Long productId);

}