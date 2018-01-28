package cn.wolfcode.shop.service.impl;

import cn.wolfcode.shop.domain.*;
import cn.wolfcode.shop.mapper.ProductSkuMapper;
import cn.wolfcode.shop.mapper.ProductSkuPropertyMapper;
import cn.wolfcode.shop.service.ICatalogService;
import cn.wolfcode.shop.service.IProductService;
import cn.wolfcode.shop.service.IProductSkuService;
import cn.wolfcode.shop.viewObject.ProductVo;
import cn.wolfcode.shop.viewObject.SkuGenerateFormVo;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ProductSkuServiceImpl implements IProductSkuService {
    @Autowired
    private ProductSkuMapper productSkuMapper;
    @Autowired
    private ProductSkuPropertyMapper productSkuPropertyMapper;

    @Autowired
    private IProductService productService;
    @Autowired
    private ICatalogService catalogService;


    @Override
    public List<ProductSku> getSkuByProductId(Long productId) {
        List<ProductSku> skuByProducts= productSkuMapper.getSkuByProductId(productId);
        return skuByProducts;
    }

    /**
     * 生成SKU
     *
     * @param vo
     * @return
     */
    @Override
    public List<Map<String, Object>> generateSku(SkuGenerateFormVo vo) {
        //获取商品
        Product product = productService.get(vo.getProductId());

        //获取sku属性集合
        List<SkuProperty> skuPropertieList = vo.getSkuPropertyList();

        //获取sku属性值的集合
        List<SkuPropertyValue> skuPropertyValueList = vo.getSkuPropertyValueList();

        //生成sku编码前缀

        String skuCodePrefix = getSkuCodePrefix(product);


        Map<Long, List<SkuPropertyValue>> skuPropertyAndValueMap = getSkuPropertyAndValueMap(skuPropertieList, skuPropertyValueList);

        //把map转换为list集合/嵌套集合
        List<List<SkuPropertyValue>> handleList = new ArrayList();
        Iterator<SkuProperty> skuPropertyIterator = skuPropertieList.iterator();
        while (skuPropertyIterator.hasNext()) {
            SkuProperty skuProperty = skuPropertyIterator.next();
            handleList.add(skuPropertyAndValueMap.get(skuProperty.getId()));
        }


        List<List<String>> recursiveResult = new ArrayList();//用于存储递归结果
        recursive(handleList, 0, new ArrayList(), recursiveResult);

        //定义一个集合 用于存储商品的每一条SKU
        List<Map<String, Object>> pageResult = new ArrayList();

        for (int i = 0; i < recursiveResult.size(); ++i) {

            Map<String, Object> map = new HashMap();

            //sku 编码
            map.put("code", skuCodePrefix + i);

            //设置具体的属性及属性值
            for (int j = 0; j < recursiveResult.get(i).size(); ++j) {
                map.put((skuPropertieList.get(j)).getName(), recursiveResult.get(i).get(j));
            }

            map.put("price", product.getBasePrice());
            pageResult.add(map);
        }
        return pageResult;
    }


    /**
     * 把属性集合 和属性值集合 并成一个List<List<SkuPropertyValue>>
     *
     * @param skuPropertieList
     * @param skuPropertyValueList
     * @return
     */
    private Map<Long, List<SkuPropertyValue>> getSkuPropertyAndValueMap(List<SkuProperty> skuPropertieList, List<SkuPropertyValue> skuPropertyValueList) {

        Map<Long, List<SkuPropertyValue>> skuPropertyAndValueMap = new HashMap();

        for (int i = 0; i < skuPropertieList.size(); ++i) {
            Long skuPropertyId = skuPropertieList.get(i).getId();

            if (!skuPropertyAndValueMap.containsKey(skuPropertyId)) {
                //map 中创建一个以sku属性id为key的集合
                skuPropertyAndValueMap.put(skuPropertyId, new ArrayList());
            }

            //遍历 sku属性值集合 把对应属性值放入到对应的属性中
            for (int j = 0; j < skuPropertyValueList.size(); ++j) {
                Long skuPropertyIdOfValue = skuPropertyValueList.get(j).getSkuProperty().getId();
                if (skuPropertyId.equals(skuPropertyIdOfValue)) {
                    skuPropertyAndValueMap.get(skuPropertyId).add(skuPropertyValueList.get(j));
                }
            }
        }
        return skuPropertyAndValueMap;
    }


    /**
     * 生成sku代码的
     *
     * @param product
     * @return
     */
    private String getSkuCodePrefix(Product product) {
        String skuCodePrefix = product.getId() > 9 ? "" + product.getId() : "0" + product.getId();

        Long catalogId = product.getCatalog().getId();
        //获取当前分类
        Catalog catalog = catalogService.getCatalog(catalogId);

        //获取当前分类的顺序大于0 就拼接,小于9就加个0
        skuCodePrefix = catalog.getSequence() > 9 ? "" + catalog.getSequence() : "0" + catalog.getSequence() + skuCodePrefix;
        //获取分类的编码的前两位
        String catalogCode = catalog.getCode().substring(0, 2).toUpperCase();
        //获取当前的分类所属父类的id
        Long parentCatalogId = catalog.getParentCatalogId();
        while (true) {
            Catalog catalogParent = catalogService.getCatalogByParentId(parentCatalogId);
            if (catalogParent == null) {
                skuCodePrefix = catalogCode + skuCodePrefix.substring(2, skuCodePrefix.length());
                return skuCodePrefix;
            }
            //获取当前父类的顺序
            skuCodePrefix = catalogParent.getSequence() > 9 ? "" + catalogParent.getSequence() : "0" + catalogParent.getSequence() + skuCodePrefix;
            parentCatalogId = catalogParent.getParentCatalogId();
            catalogCode = catalogParent.getCode().substring(0, 2).toUpperCase();
        }
    }

    /**
     * 递归生成 sku
     *
     * @param handleList      前台创过来的 sku属性的值的集合
     * @param index           属性值集合的索引
     * @param empList         用于存储运行是的存储数据的中间集合
     * @param recursiveResult 递归后的sku结果集
     */
    private void recursive(List<List<SkuPropertyValue>> handleList, int index, List<String> empList, List<List<String>> recursiveResult) {

        List<String> newList;
        if (index < handleList.size() - 1) {
            if (handleList.get(index).size() == 0) {
                recursive(handleList, index + 1, empList, recursiveResult);

            } else {
                for (int i = 0; i < handleList.get(index).size(); i++) {
                    newList = new ArrayList<String>(empList);
                    newList.add((handleList.get(index).get(i)).getValue());//把属性值放到数组中
                     
                    recursive(handleList, index + 1, newList, recursiveResult);
                }
            }
        } else if ((index == handleList.size() - 1)) {
            if (handleList.get(index).size() == 0) {
                recursiveResult.add(empList);
            } else {
                for (int i = 0; i < handleList.get(index).size(); i++) {

                    newList = new ArrayList<String>(empList);
                    newList.add(handleList.get(index).get(i).getValue());

                    //最终的SKU 保存到集合中
                    recursiveResult.add(newList);
                }
            }
        }

    }

    /**
     * 保存生成的sku信息
     *
     * @param vo
     */
    @Override
    public void save(ProductVo vo) {
        //获取页面的sku列表
        List<ProductSku> productSkuList = vo.getProductSkuList();

        //生成迭代对象
        Iterator<ProductSku> productSkuIterator = productSkuList.iterator();

        while (productSkuIterator.hasNext()) {
            ProductSku productSku = productSkuIterator.next();
            //设置mods的值
            productSku.setMods(1L);

            productSku.setProduct(vo.getProduct());
            this.productSkuMapper.insert(productSku);
            List<ProductSkuProperty> productSkuPropertyList = productSku.getProductSkuPropertyList();
            Iterator<ProductSkuProperty> skuPropertyIterator = productSkuPropertyList.iterator();

            while (skuPropertyIterator.hasNext()) {
                ProductSkuProperty productSkuProperty = skuPropertyIterator.next();
                productSkuProperty.setProductSku(productSku);
                productSkuPropertyMapper.insert(productSkuProperty);
            }
        }
    }
}
