package cn.wolfcode.shop.service.impl;


import cn.wolfcode.shop.domain.Brand;
import cn.wolfcode.shop.mapper.BrandMapper;
import cn.wolfcode.shop.util.PageResult;
import cn.wolfcode.shop.query.BrandQueryObject;
import cn.wolfcode.shop.service.IBrandService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(timeout = 500000)
@Transactional
public class BrandServiceImpl implements IBrandService {
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageResult query(BrandQueryObject qo) {
        int count = brandMapper.queryForCount(qo);
        if (count == 0) {
            return PageResult.empty(qo.getPageSize());
        }
        return new PageResult(brandMapper.queryForList(qo), count, qo.getCurrentPage(), qo.getPageSize());
    }

    @Override
    public void save(Brand brand, Long[] catIds) {
        if (brand != null) {
            brandMapper.insert(brand);
            //System.out.println(100/0+"================");
            // 维护关系中间表
            if (catIds != null) {
                for (Long id : catIds) {
                    brandMapper.insertBrandCatalogRelation(brand.getId(), id);
                }
            }
        } else {
            throw new RuntimeException("保存失败!");
        }
    }

    @Override
    public void updata(Brand brand, Long[] catIds) {
        if (brand != null) {
            //打破关系
            brandMapper.deleteBrandCatalogRelation(brand.getId());
            brandMapper.updateByPrimaryKey(brand);
            // 维护关系中间表
            if (catIds != null) {
                for (Long id : catIds) {
                    brandMapper.insertBrandCatalogRelation(brand.getId(), id);
                }
            }
        } else {
            throw new RuntimeException("修改失败!");
        }
    }

    @Override
    public void savePhoto(String filePath) {

    }

    @Override
    public List<Brand> selectAll() {

        return  brandMapper.selectAll();
    }

    @Override
    public void delete(Long id) {
        brandMapper.deleteBrandCatalogRelation(id);
        brandMapper.deleteByPrimaryKey(id);
    }

}
