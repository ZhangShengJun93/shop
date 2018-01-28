package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.ProductDesc;
import cn.wolfcode.shop.mapper.ProductDescMapper;
import cn.wolfcode.shop.service.IProductDescService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(timeout = 5000)
@Transactional
public class ProductDescServiceImpl implements IProductDescService {
    @Autowired
    private ProductDescMapper productDescMapper;

    /**
     * 通过商品id查询商品的详情
     * @param productId
     * @return
     */
    @Override
    public ProductDesc get(Long productId) {

        return productDescMapper.selectByProductId(productId);
    }

    /**
     * 插入商品详情
     * @param productDesc
     * @param productId
     */
    @Override
    public void insert(ProductDesc productDesc, Long productId) {
        //一些必要的判断
        ProductDesc newProductDesc = new ProductDesc();
        newProductDesc.setDetails(productDesc.getDetails());
        newProductDesc.setProductId(productId);
        productDescMapper.insert(newProductDesc);
    }

    @Override
    public void update(ProductDesc productDesc, Long productId) {
        productDesc.setProductId(productId);
        productDescMapper.updateByPrimaryKey(productDesc);
    }
}
