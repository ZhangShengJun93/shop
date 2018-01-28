package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.*;
import cn.wolfcode.shop.service.IProductService;
import cn.wolfcode.shop.service.IProductSkuService;
import cn.wolfcode.shop.service.ISkuPropertyService;
import cn.wolfcode.shop.util.JSONResult;
import cn.wolfcode.shop.viewObject.ProductVo;
import cn.wolfcode.shop.viewObject.SkuGenerateFormVo;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("productSku")
@Api(value = "商品的SKU", description = "商品的SKU管理", produces = "application/json", protocols = "https")
public class ProductSkuController {
    @Reference
    private IProductSkuService productSkuService;
    @Reference
    private IProductService productService;
    @Reference
    private ISkuPropertyService skuPropertyService;

    /**
     * @param model
     * @param catalogId
     * @return
     */
    @RequestMapping(value = "/getSkuPropertyPanel", method = RequestMethod.GET)
    public String getSkuProperty(Model model, Long catalogId) {
        List<SkuProperty> skuPropertyList = skuPropertyService.getSkuPropertyByCatalogId(catalogId);
        model.addAttribute("skuPropertyList", skuPropertyList);
        return "product/sku_property_panel";
    }

    /**
     * 或跳转到生成sku的界面
     *
     * @param productId
     * @param model
     * @return
     */
    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    @ApiOperation(value = "跳转到生成sku页面", notes = "通过sku属性列表生成sku",
            httpMethod = "GET", consumes = "application/x-www-form-urlencoded")
    @ApiImplicitParams({@ApiImplicitParam(name = "productId", value = "产品id",
            required = true, dataType = "Long", paramType = "query")
    })
    public String toGenerateSkuUI(@PathVariable("productId") Long productId, Model model) {
        //获取产品信息
        Product product = productService.get(productId);
        model.addAttribute("product", product);
        //获取产品所对应的sku列表
        List<ProductSku> productSkuList = productSkuService.getSkuByProductId(productId);
        //如果生成过sku 
        if (productSkuList != null && productSkuList.size() > 0) {
            model.addAttribute("productSkuList", productSkuList);

            //迭代列表
            LinkedHashSet<String> set = new LinkedHashSet();
            //生成sku迭代器
            Iterator<ProductSku> iterator = productSkuList.iterator();

            while (iterator.hasNext()) {
                ProductSku productSku = iterator.next();
                //获取sku中sku属性的迭代器
                Iterator propertyIterator = productSku.getProductSkuPropertyList().iterator();

                while (propertyIterator.hasNext()) {

                    ProductSkuProperty productSkuProperty = (ProductSkuProperty) propertyIterator.next();
                    set.add(productSkuProperty.getSkuProperty().getName());
                }
            }

            model.addAttribute("skuPropertyList", set);
            return "product/sku_manage";

        } else {
            List<SkuProperty> skuPropertyList = skuPropertyService.getSkuPropertyByCatalogId(product.getCatalog().getId());
            model.addAttribute("skuPropertyList", skuPropertyList);
            return "product/sku_generate";
        }
    }


    /**
     * 显示属性值列表
     *
     * @param model
     * @param skuPropertyId
     * @return
     */
    @RequestMapping(value = "/skuPropertyValue/{skuPropertyId}", method = RequestMethod.GET)
    @ApiOperation(value = "打卡显示属性值列表", notes = "显示出shu属性值的列表",
            httpMethod = "GET", consumes = "application/x-www-form-urlencoded")
    @ApiImplicitParams({@ApiImplicitParam(name = "skuPropertyId", value = "SKU属性id",
            required = true, dataType = "Long", paramType = "query")
    })
    public String getSkuPropertyValueTable(Model model, @PathVariable("skuPropertyId") Long skuPropertyId) {
        SkuProperty skuProperty = skuPropertyService.getSkuProperty(skuPropertyId);
        List<SkuPropertyValue> skuPropertyValue = skuPropertyService.getValueByskuPropertyId(skuPropertyId);
        model.addAttribute("skuPropertyValue", skuPropertyValue);
        model.addAttribute("skuProperty", skuProperty);
        return "product/sku_property_value_table";
    }

    /**
     * 生成sku列表
     *
     * @param model
     * @param vo
     * @return
     */
    @RequestMapping(value = "/generateSku", method = RequestMethod.POST)
    @ApiOperation(value = "打卡显示属性值列表", notes = "显示出shu属性值的列表",
            httpMethod = "POST", consumes = "application/x-www-form-urlencoded")
    @ApiImplicitParams({@ApiImplicitParam(name = "productId", value = "生成的SKU是属于哪个产品的",
            required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "SkuPropertyList", value = "计算SKU所需属性的集合",
                    required = true, dataType = "List", paramType = "form"),
            @ApiImplicitParam(name = "SkuPropertyValueList", value = "计算SKU所需属性值的集合",
                    required = true, dataType = "List", paramType = "form")
    })
    public String generateSkuList(Model model, @RequestBody SkuGenerateFormVo vo) {

        List<Map<String, Object>> skuList = productSkuService.generateSku(vo);

        model.addAttribute("skuList", skuList);
        model.addAttribute("skuPropertieList", vo.getSkuPropertyList());
        return "product/sku_form";
    }

    /**
     * 保存sku列表
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "打卡显示属性值列表", notes = "显示出shu属性值的列表",
            httpMethod = "POST", consumes = "application/x-www-form-urlencoded", response = JSONResult.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "productSkuList", value = "保存了SKU的属性的集合",
            required = true, dataType = "List", paramType = "form"),
            @ApiImplicitParam(name = "product", value = "保存产品信息的参数",
                    required = true, dataType = "product.class", paramType = "form")
    })
    @ResponseBody
    public JSONResult productSkuSave(ProductVo vo) {
        JSONResult jsonResult = new JSONResult();
        try {
            productSkuService.save(vo);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMessage(e.getMessage());
        }
        return jsonResult;
    }
}
