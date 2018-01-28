package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.Product;
import cn.wolfcode.shop.query.ProductQueryObject;
import cn.wolfcode.shop.util.PageResult;
import cn.wolfcode.shop.viewObject.ProductInfoVo;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    PageResult query(ProductQueryObject qo);

    /**
     * 查询商品的基本信息
     * @param id
     * @return
     */
    Product get(Long id);

    /**
     * 插入商品所有信息
     * @param productInfoVo
     */
    void insert(ProductInfoVo productInfoVo);

    /**
     * 修改商品信息
     * @param productInfoVo
     */
    void update(ProductInfoVo productInfoVo);

}
