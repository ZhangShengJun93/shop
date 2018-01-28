package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.CatalogProperty;
import cn.wolfcode.shop.domain.CatalogPropertyValue;
import cn.wolfcode.shop.service.ICatalogPropertyService;
import cn.wolfcode.shop.service.ICatalogPropertyValueService;
import cn.wolfcode.shop.util.JSONResult;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("catalogPropertyValue")
public class CatalogPropertyValueController {
    @Reference
    private ICatalogPropertyValueService catalogPropertyValueService;
    @Reference
    private ICatalogPropertyService catalogPropertyService;

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public String getCatalogPropertyValue(@PathVariable("id") Long CatalogPropertyId, Model model) {
        CatalogProperty catalogProperty = catalogPropertyService.selectCatalogPropertyByCatalogPropertyId(CatalogPropertyId);
        List<CatalogPropertyValue> catalogPropertyValues =
                catalogPropertyValueService.selectCatalogPropertyValueByCatalogPropertyId(CatalogPropertyId);
        model.addAttribute("list", catalogPropertyValues);
        model.addAttribute("catalogProperty", catalogProperty);
        return "property/property_value_list";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public JSONResult getCatalogPropertyValue(CatalogPropertyValue catalogPropertyValue, String[] values) {

        JSONResult jsonResult = new JSONResult();
        try {
            if(catalogPropertyValue.getValue()!=null){
                int ret = catalogPropertyValueService.addCatalogPropertyValue(catalogPropertyValue, values);
            }else {
                Long catalogPropertyId = catalogPropertyValue.getCatalogProperty().getId();
                if (catalogPropertyId != null) {
                    if (values != null) {
                        int ret = catalogPropertyValueService.addCatalogPropertyValue(catalogPropertyValue, values);
                        if (ret == 0) {
                            throw new RuntimeException("操作失败");
                        }
                    } else {
                        throw new RuntimeException("没有要添加的属性值");
                    }
                } else {
                    throw new RuntimeException("该属性值没有属性");
                }
            }

        } catch (Exception e) {
            jsonResult.setMessage(e.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JSONResult deleteCatalogPropertyValue(@PathVariable("id") Long catalogPropertyValueId) {

        JSONResult jsonResult = new JSONResult();
        try {
            if (catalogPropertyValueId != null) {
                int ret = catalogPropertyValueService.deleteCatalogPropertyValue(catalogPropertyValueId);
                if (ret == 0) {
                    throw new RuntimeException("删除失败");
                }
            } else {
                throw new RuntimeException("没有要删除的属性值");
            }
        } catch (Exception e) {
            jsonResult.setMessage(e.getMessage());
        }
        return jsonResult;
    }

}
