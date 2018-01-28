package cn.wolfcode.shop.viewObject;

import cn.wolfcode.shop.domain.Product;
import cn.wolfcode.shop.domain.ProductCatalogPropertyValue;
import cn.wolfcode.shop.domain.ProductDesc;
import cn.wolfcode.shop.domain.ProductImage;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class ProductInfoVo implements Serializable{
    private Product product;
    private ProductDesc productDesc;
    private List<ProductImage> productImages = new ArrayList<ProductImage>();
    private List<ProductCatalogPropertyValue> productCatalogPropertyValues = new ArrayList<ProductCatalogPropertyValue>();
}
