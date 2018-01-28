package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.CatalogPropertyValue;
import cn.wolfcode.shop.mapper.CatalogPropertyValueMapper;
import cn.wolfcode.shop.service.ICatalogPropertyValueService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CatalogPropertyValueServiceImpl implements ICatalogPropertyValueService {
    @Autowired
    private CatalogPropertyValueMapper catalogPropertyValueMapper;

    @Override
    public List<CatalogPropertyValue> selectCatalogPropertyValueByCatalogPropertyId(Long catalogPropertyId) {
        return catalogPropertyValueMapper.selectCatalogPropertyValueByCatalogPropertyId(catalogPropertyId);
    }

    @Override
    public int addCatalogPropertyValue(CatalogPropertyValue catalogPropertyValue, String[] values) {
        int ret = 0;
        if (catalogPropertyValue.getValue() != null) {
            Long catalogPropertyId = catalogPropertyValue.getCatalogProperty().getId();
           // System.out.println(catalogPropertyId+"++++++++++++++");
            CatalogPropertyValue value = catalogPropertyValueMapper.selectByCatalogPropertyId(catalogPropertyId);
           // System.out.println(value+"=============");
            if (value == null) {
                catalogPropertyValue.setSequence(1);
                int insert = catalogPropertyValueMapper.insert(catalogPropertyValue);
                ret = insert;
            } else {
                int i = catalogPropertyValueMapper.updateByPrimaryKey(catalogPropertyValue);
                ret = i;
            }
        } else {
            for (int i = 0; i < values.length; i++) {
                //System.out.println(values[i]);
                catalogPropertyValue.setValue(values[i]);
                catalogPropertyValue.setSequence(1);
                int insert = catalogPropertyValueMapper.insert(catalogPropertyValue);
                ret = ret + insert;
            }
        }
        return ret;
    }

    @Override
    public int deleteCatalogPropertyValue(Long catalogPropertyValueId) {
        return catalogPropertyValueMapper.deleteByPrimaryKey(catalogPropertyValueId);
    }
}
