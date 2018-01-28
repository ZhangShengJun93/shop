package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.ProductImage;

import java.util.List;

public interface IProductImageService {

    List<ProductImage> getByProductId(Long productId);

    void insert(List<ProductImage> productImages, Long productId);

    /**
     * 更新商品图片
     * @param productImages
     * @param productId
     */
    void update(List<ProductImage> productImages, Long productId);

}
