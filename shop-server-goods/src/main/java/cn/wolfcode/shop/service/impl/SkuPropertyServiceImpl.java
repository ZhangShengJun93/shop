package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.SkuProperty;
import cn.wolfcode.shop.domain.SkuPropertyValue;
import cn.wolfcode.shop.mapper.SkuPropertyMapper;
import cn.wolfcode.shop.mapper.SkuPropertyValueMapper;
import cn.wolfcode.shop.service.ISkuPropertyService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Service
public class SkuPropertyServiceImpl implements ISkuPropertyService {
    @Autowired
    private SkuPropertyMapper skuPropertyMapper;
    @Autowired
    private SkuPropertyValueMapper skuPropertyValueMapper;

    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    @Override
    public List<SkuProperty> getSkuPropertyByCatalogId(Long catalogId) {

        return skuPropertyMapper.getSkuPropertyByCatalogId(catalogId);
    }

    @Override
    public SkuProperty getSkuProperty(Long skuPropertyId) {
        return skuPropertyMapper.selectByPrimaryKey(skuPropertyId);
    }

    @Override
    public void update(SkuProperty skuProperty) {
        skuPropertyMapper.updateByPrimaryKey(skuProperty);
    }

    @Override
    public void insert(SkuProperty skuProperty) {
        skuPropertyMapper.insert(skuProperty);

    }

    @Override
    public void delete(Long id) {
        skuPropertyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<SkuPropertyValue> getValueByskuPropertyId(Long skuPropertyId) {
        return skuPropertyValueMapper.getValueByskuPropertyId(skuPropertyId);
    }

    @Override
    public void insertPropertyValue(SkuPropertyValue skuPropertyValue) {
        skuPropertyValueMapper.insert(skuPropertyValue);
    }

    @Override
    public void deleteValueById(Long skuPropetryValueId) {
        skuPropertyValueMapper.deleteByPrimaryKey(skuPropetryValueId);
    }


}
