package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.Product;
import cn.wolfcode.shop.domain.ProductCatalogPropertyValue;
import cn.wolfcode.shop.domain.ProductDesc;
import cn.wolfcode.shop.domain.ProductImage;
import cn.wolfcode.shop.exception.DisplayableException;
import cn.wolfcode.shop.mapper.ProductMapper;
import cn.wolfcode.shop.query.ProductQueryObject;
import cn.wolfcode.shop.service.IProductCatalogPropertyValueService;
import cn.wolfcode.shop.service.IProductDescService;
import cn.wolfcode.shop.service.IProductImageService;
import cn.wolfcode.shop.service.IProductService;
import cn.wolfcode.shop.util.PageResult;
import cn.wolfcode.shop.viewObject.ProductInfoVo;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Transactional
@Service(timeout = 5000)
public class ProductServiceImpl implements IProductService {
    /**
     * 添加注释的test 用作第一次上传测试
     */
    @Autowired
    ProductMapper productMapper;
    @Autowired
    private IProductDescService productDescService;
    @Autowired
    private IProductImageService productImageService;
    @Autowired
    private IProductCatalogPropertyValueService productCatalogPropertyValueService;

    @Override
    public List<Product> getAll() {
        List<Product> products = productMapper.selectAll();
        return products;
    }

    @Override
    public PageResult query(ProductQueryObject qo) {
        if (qo.getCatalogIds().length > 0){
            qo.setCatalogIdsFirst(1L);
        }
        int count = productMapper.queryForCount(qo);
        if (count == 0){
           return PageResult.empty(qo.getPageSize());
        }
        List<Product> list = productMapper.queryForList(qo);
        return new PageResult(list,count,qo.getCurrentPage(),qo.getPageSize());
    }


    @Override
    public Product get(Long productId) {
        return productMapper.selectByPrimaryKey(productId);
    }

    /**
     * 插入商品所有信息
     * @param productInfoVo
     */
    @Override
    public void insert(ProductInfoVo productInfoVo) {
        //一些判断语句
        Product product = productInfoVo.getProduct();
        ProductDesc productDesc = productInfoVo.getProductDesc();
        List<ProductCatalogPropertyValue> productCatalogPropertyValues = productInfoVo.getProductCatalogPropertyValues();
        List<ProductImage> productImages = productInfoVo.getProductImages();
        if (product.getBasePrice()==null){
            throw new DisplayableException("基础售价不能为空!");
        }
        if (StringUtils.isEmpty(product.getName())){
            throw new DisplayableException("商品名不能为空!");
        }

        Product product1 = insertProduct(product);
        Long productId = product1.getId();
        productDescService.insert(productDesc,productId);
        productImageService.insert(productImages,productId);
        productCatalogPropertyValueService.insert(productCatalogPropertyValues,productId);
    }

    @Override
    public void update(ProductInfoVo productInfoVo) {
        Product product = productInfoVo.getProduct();
        ProductDesc productDesc = productInfoVo.getProductDesc();
        List<ProductImage> productImages = productInfoVo.getProductImages();
        List<ProductCatalogPropertyValue> productCatalogPropertyValues = productInfoVo.getProductCatalogPropertyValues();
        updateProduct(product);
        Long productId = product.getId();
        productDescService.update(productDesc,productId);
        productImageService.update(productImages,productId);
        productCatalogPropertyValueService.update(productCatalogPropertyValues,productId);
    }
    public void updateProduct(Product product){
        Product oldProduct = productMapper.selectByPrimaryKey(product.getId());
        product.setLastModifiedDate(new Date());
        product.setCreatedDate(oldProduct.getCreatedDate());
        int count = productMapper.updateByPrimaryKey(product);
        if (count==0){
            throw new DisplayableException("修改失败[同步锁]");
        }
    }

    private Product insertProduct(Product product) {
        Product newProduct = new Product();
        newProduct.setBasePrice(product.getBasePrice());
        newProduct.setBrand(product.getBrand());
        newProduct.setCatalog(product.getCatalog());
        newProduct.setCode(product.getCode());
        newProduct.setCreatedDate(new Date());
        newProduct.setImage(product.getImage());
        newProduct.setImpressions(product.getImpressions());
        newProduct.setKeyword(product.getKeyword());
        newProduct.setLastModifiedDate(new Date());
        newProduct.setMarketPrice(product.getMarketPrice());
        newProduct.setMods(product.getMods());
        newProduct.setName(product.getName());
        productMapper.insert(newProduct);
        return newProduct;
    }


}

