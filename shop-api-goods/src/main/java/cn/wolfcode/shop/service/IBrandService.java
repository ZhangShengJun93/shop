package cn.wolfcode.shop.service;

import cn.wolfcode.shop.domain.Brand;
import cn.wolfcode.shop.util.PageResult;
import cn.wolfcode.shop.query.BrandQueryObject;

import java.util.List;


public interface IBrandService {
    /**
     * 品牌查询
     * @param qo
     * @return
     */
    PageResult query(BrandQueryObject qo);

    /**
     * 品牌删除
     * @param id
     */
    void delete(Long id);

    /**
     * 新增品牌
     * @param brand
     * @param catIds
     */
    void save(Brand brand, Long[] catIds);

    /**
     * 品牌修改
     * @param brand
     * @param catIds
     */
    void updata(Brand brand, Long[] catIds);

    /**
     * 保存图片
     * @param filePath
     */
    void savePhoto(String filePath);

    List<Brand> selectAll();

}
