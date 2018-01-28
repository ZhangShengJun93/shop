package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.CatalogProperty;
import cn.wolfcode.shop.mapper.CatalogPropertyMapper;
import cn.wolfcode.shop.service.ICatalogPropertyService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CatalogPropertyServiceImpl implements ICatalogPropertyService {
    @Autowired
    private CatalogPropertyMapper catalogPropertyMapper;

    @Override
    public List<CatalogProperty> selectByCatalogId(Long catalogId) {
        return catalogPropertyMapper.selectCatalogPropertyByCatalogId(catalogId);
    }

    @Override
    public CatalogProperty selectCatalogPropertyByCatalogPropertyId(Long catalogPropertyId) {
        return catalogPropertyMapper.selectByPrimaryKey(catalogPropertyId);
    }

    @Override
    public CatalogProperty selectByPrimaryKey(Long id) {
        return catalogPropertyMapper.selectByPrimaryKey(id);
    }

    @Override
    public int save(CatalogProperty catalogProperty) {
        catalogProperty.setSequence(1);
        return catalogPropertyMapper.insert(catalogProperty);
    }

    @Override
    public int updata(CatalogProperty catalogProperty) {
        return catalogPropertyMapper.updateByPrimaryKey(catalogProperty);
    }

    @Override
    public void delete(Long id) {
        catalogPropertyMapper.deleteByPrimaryKey(id);
    }
}
