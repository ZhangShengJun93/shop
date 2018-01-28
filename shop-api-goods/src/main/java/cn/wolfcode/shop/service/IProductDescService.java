package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.ProductDesc;

public interface IProductDescService {

    ProductDesc get(Long id);

    /**
     * 插入商品详情
     * @param productDesc
     * @param productId
     */
    void insert(ProductDesc productDesc, Long productId);

    /**
     * 更新商品详情
     * @param productDesc
     * @param productId
     */
    void update(ProductDesc productDesc, Long productId);

}
