package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.SkuProperty;
import cn.wolfcode.shop.domain.SkuPropertyValue;
import cn.wolfcode.shop.service.ISkuPropertyService;
import cn.wolfcode.shop.util.JSONResult;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("skuPropertyValue")
@Api(value = "SKU属性值相关", description = "SKU值的CRUD", produces = "application/json", protocols = "https")
public class SkuPropertyValueController {

    @Reference
    private ISkuPropertyService skuPropertyService;

    @RequestMapping(value = "get/{skuPropertyId}", method = RequestMethod.GET)
    public String get(@PathVariable("skuPropertyId") Long skuPropertyId, Model model) {

        SkuProperty skuProperty = skuPropertyService.getSkuProperty(skuPropertyId);

        List<SkuPropertyValue> skuPropertyValueList = skuPropertyService.getValueByskuPropertyId(skuPropertyId);

        model.addAttribute("list", skuPropertyValueList);

        model.addAttribute("skuProperty", skuProperty);

        return "sku/property_value_list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public JSONResult addSkuPropertyValue(SkuPropertyValue skuPropertyValue, String[] propertys) {
        JSONResult jSONResult = new JSONResult();

        try {
            if (propertys != null) {
                for (String value : propertys) {
                    List<SkuPropertyValue> amoount = skuPropertyService.getValueByskuPropertyId(skuPropertyValue.getSkuProperty().getId());
                    skuPropertyValue.setValue(value);
                    skuPropertyValue.setSequence(amoount.size() + 1);
                    skuPropertyService.insertPropertyValue(skuPropertyValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            jSONResult.setMessage(e.getMessage());
        }
        return jSONResult;
    }

    @RequestMapping(value = "/delete/{skuPropetryValueId}", method = RequestMethod.GET)
    @ResponseBody
    public JSONResult addSkuPropertyValue(@PathVariable("skuPropetryValueId") Long skuPropetryValueId) {
        JSONResult jSONResult = new JSONResult();

        try {
            skuPropertyService.deleteValueById(skuPropetryValueId);
        } catch (Exception e) {
            e.printStackTrace();
            jSONResult.setMessage(e.getMessage());
        }
        return jSONResult;
    }
}
