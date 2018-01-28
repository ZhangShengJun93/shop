package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.ProductImage;
import cn.wolfcode.shop.mapper.ProductImageMapper;
import cn.wolfcode.shop.service.IProductImageService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(timeout = 5000)
@Transactional
public class ProductImageServiceImpl implements IProductImageService {
    @Autowired
    private ProductImageMapper productImageMapper;


    @Override
    public List<ProductImage> getByProductId(Long productId) {
        return productImageMapper.selectByProductId(productId);
    }

    /**
     * 插入指定商品图片
     *
     * @param productImages
     * @param productId
     */
    @Override
    public void insert(List<ProductImage> productImages, Long productId) {
        for (ProductImage productImage : productImages) {
            //做一些基本的判断
            ProductImage newProductImage = new ProductImage();
            newProductImage.setCover(productImage.getCover());
            newProductImage.setImagePath(productImage.getImagePath());
            newProductImage.setMods(productImage.getMods());
            newProductImage.setProductId(productId);
            newProductImage.setSequence(productImage.getSequence());
            productImageMapper.insert(newProductImage);
        }
    }

    @Override
    public void update(List<ProductImage> productImages, Long productId) {
       // List<ProductImage> oldProductImages = productImageMapper.selectByProductId(productId);
       /* for (int i = 0; i < productImages.size(); i++) {
            Long id = oldProductImages.get(i).getId();
            productImages.get(i).setId(id);
            productImages.get(i).setProductId(productId);
            productImageMapper.updateByPrimaryKey(productImages.get(i));
        }*/
        for (ProductImage productImage : productImages) {
            productImage.setProductId(productId);
            productImageMapper.updateByPrimaryKey(productImage);
        }
    }
}
