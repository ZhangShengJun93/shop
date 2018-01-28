package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.Catalog;
import cn.wolfcode.shop.mapper.CatalogMapper;
import cn.wolfcode.shop.service.ICatalogService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service(timeout = 5000)
@Transactional
public class CatalogServiceImpl implements ICatalogService {
    @Autowired
    private CatalogMapper catalogMapper;

    @Override
    public List<Catalog> getCatalog() {
        return catalogMapper.selectAll();
    }


    @Override
    public Catalog getCatalog(Long catalogId) {

        return catalogMapper.selectByPrimaryKey(catalogId);
    }

    @Override
    public Catalog getCatalogByParentId(Long parentCatalogId) {
        return catalogMapper.getCatalogByParentId(parentCatalogId);
    }

    @Override
    public List<Catalog> selectAll() {

        return catalogMapper.selectAll();
    }

    @Override
    public List<Map<String, Object>> getChildenCatalogById(Long parentId) {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<Catalog> catalogs = catalogMapper.getChildenCatalogById(parentId);

        for (Catalog catalog : catalogs) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", catalog.getId());
            map.put("text", catalog.getName());
            if (catalog.getIsParent() == 1) {
                map.put("state", "closed");
            }
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Catalog> getChildenCatalogByIds(Long parentId) {

        List<Catalog> catalogs = catalogMapper.getChildenCatalogByIds(parentId);

        return catalogs;
    }

    @Override
    public Catalog selectByCatalogId(Long catalogId) {
        return catalogMapper.selectByPrimaryKey(catalogId);
    }

    @Override
    public int save(Catalog catalog) {
        catalog.setCreateDate(new Date());
        Long parentCatalogId = catalog.getParentCatalogId();
        if (parentCatalogId != null) {
            //根据父类id查询父类信息
            Catalog ParentCatalog = catalogMapper.selectInfParentByparentCatalogId(parentCatalogId);
            //如果是父类将其排序依次递增1
            if (parentCatalogId == 1) {
                catalog.setIsParent(1);
                catalog.setSequence(ParentCatalog.getSequence() + 1);
                catalog.setLevel(1);
            } else {
                catalog.setSequence(ParentCatalog.getSequence() + 1);
                catalog.setIsParent(0);
                catalog.setLevel(2);
            }
        }else{

        }
        int insertResult = catalogMapper.insert(catalog);
        return insertResult;
    }

    @Override
    public int updata(Catalog catalog) {
        //根据id获取当前分类对象
        Catalog oldCatalog = catalogMapper.selectByPrimaryKey(catalog.getId());
        oldCatalog.setCode(catalog.getCode());
        oldCatalog.setName(catalog.getName());
        oldCatalog.setLastModifiedDate(new Date());
        int updateResult = catalogMapper.updateByPrimaryKey(oldCatalog);
        return updateResult;
    }

    @Override
    public int deleteByCatalogId(Long catalogId) {
        return catalogMapper.deleteByPrimaryKey(catalogId);
    }

}
