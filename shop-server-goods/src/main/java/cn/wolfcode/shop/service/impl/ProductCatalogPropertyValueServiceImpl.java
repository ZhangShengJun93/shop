package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.ProductCatalogPropertyValue;
import cn.wolfcode.shop.exception.DisplayableException;
import cn.wolfcode.shop.mapper.ProductCatalogPropertyValueMapper;
import cn.wolfcode.shop.service.IProductCatalogPropertyValueService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(timeout = 5000)
@Transactional
public class ProductCatalogPropertyValueServiceImpl implements IProductCatalogPropertyValueService {
    @Autowired
    private ProductCatalogPropertyValueMapper productCatalogPropertyValueMapper;

    @Override
    public List<Map<String, Object>> getAllPropertiesByProductId(Long productId) {
        List<ProductCatalogPropertyValue> properties = productCatalogPropertyValueMapper.getAllPropertiesByProductId(productId);
        //颜色:绿
        //颜色:黄
        //尺寸:大
        //尺寸:小
        Map<String, StringBuilder> map1 = new HashMap<>();
        for (ProductCatalogPropertyValue property : properties) {

            StringBuilder stringBuilder = map1.get(property.getName());
            if (stringBuilder!=null){
                stringBuilder.append(","+property.getValue());
            }else {
                StringBuilder stringBuilder1 = new StringBuilder(property.getValue());
                map1.put(property.getName(),stringBuilder1);
            }
        }
        List<Map<String,Object>> maps = new ArrayList<>();
        for (String s : map1.keySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("name",s);
            map.put("value",map1.get(s));
            maps.add(map);
        }
        return maps;
    }

    @Override
    public List<Map<String, Object>> getCommonPropertiesByCatalogId(Long catalogId) {
        List<ProductCatalogPropertyValue> properties = productCatalogPropertyValueMapper.getCommonPropertiesByCatalogId(catalogId);

        Map<String, StringBuilder> map1 = new HashMap<>();
        for (ProductCatalogPropertyValue property : properties) {
            System.out.println(property);
            StringBuilder stringBuilder = map1.get(property.getName());
            if (stringBuilder!=null){
                stringBuilder.append(","+property.getValue());
            }else {
                StringBuilder stringBuilder1 = new StringBuilder(property.getValue());
                map1.put(property.getName(),stringBuilder1);
            }
        }
        List<Map<String,Object>> maps = new ArrayList<>();
        for (String s : map1.keySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("name",s);
            map.put("value",map1.get(s));
            maps.add(map);
        }
        return maps;
    }

    @Override
    public void insert(List<ProductCatalogPropertyValue> productCatalogPropertyValues, Long productId) {
        if (productCatalogPropertyValues==null){
            throw new DisplayableException("商品属性不能为空!");
        }
        for (ProductCatalogPropertyValue pcpv : productCatalogPropertyValues) {
            ProductCatalogPropertyValue newPcpv = new ProductCatalogPropertyValue();
            newPcpv.setName(pcpv.getName());
            newPcpv.setValue(pcpv.getValue());
            newPcpv.setProductId(productId);
            productCatalogPropertyValueMapper.insert(newPcpv);
        }
    }

    @Override
    public void update(List<ProductCatalogPropertyValue> productCatalogPropertyValues, Long productId) {
        List<ProductCatalogPropertyValue> oldProperties = productCatalogPropertyValueMapper.selectByProductId(productId);
        for (int i = 0; i < productCatalogPropertyValues.size(); i++) {
            ProductCatalogPropertyValue pcpv = productCatalogPropertyValues.get(i);
            Long id = oldProperties.get(i).getId();
            pcpv.setId(id);
            pcpv.setProductId(productId);
            productCatalogPropertyValueMapper.updateByPrimaryKey(pcpv);
        }
    }
}
