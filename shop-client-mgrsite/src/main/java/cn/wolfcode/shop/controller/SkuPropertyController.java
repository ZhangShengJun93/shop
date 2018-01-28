package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.SkuProperty;
import cn.wolfcode.shop.service.ISkuPropertyService;
import cn.wolfcode.shop.util.JSONResult;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("skuProperty")
@Api(value = "SKU属性相关", description = "SKU的CRUD", produces = "application/json", protocols = "https")
public class SkuPropertyController {

    @Reference
    private ISkuPropertyService skuPropertyService;


    @RequestMapping(method = {RequestMethod.GET})
    public String skuProperty(Model model) {
        return "sku/property";
    }

    /**
     * 获取分类列表
     * @param catalogId
     * @param model
     * @return
     */
    @RequestMapping(value = "/get/{catalogId}", method = RequestMethod.GET)
    @ApiOperation(value = "打卡显示属性值列表", notes = "显示出shu属性值的列表",
            httpMethod = "GET", consumes = "application/x-www-form-urlencoded")
    @ApiImplicitParams({@ApiImplicitParam(name = "catalogId", value = "分类属性id",
            required = true, dataType = "Long", paramType = "query")
    })
    public String getSkuProperty(@PathVariable("catalogId") Long catalogId, Model model) {
        List<SkuProperty> list = skuPropertyService.getSkuPropertyByCatalogId(catalogId);
        model.addAttribute("list", list);
        return "sku/property_list";
    }

    /**
     * 保存sku属性分类
     *
     * @param skuProperty
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "保存SKU属性值",notes = "保存表单中的填写的SKU属性值",
            httpMethod = "POST",consumes = "application/x-www-form-urlencoded",response = JSONResult.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "skuProperty", value = "sku属性对象",
            required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "skuProperty", value = "sku属性对象",
                    required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "skuProperty", value = "sku属性对象",
                    required = true, dataType = "Long", paramType = "form")
    })
    @ResponseBody
    public JSONResult addSkuProperty(SkuProperty skuProperty) {
        JSONResult jSONResult = new JSONResult();

        try {
            if (skuProperty.getId() != null && skuProperty.getId() != -1L) {
                SkuProperty skuPropertyDb = this.skuPropertyService.getSkuProperty(skuProperty.getId());
                skuPropertyDb.setName(skuProperty.getName());
                skuPropertyDb.setType(skuProperty.getType());
                skuPropertyService.update(skuPropertyDb);
            } else {
                skuProperty.setSequence(0);
                this.skuPropertyService.insert(skuProperty);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jSONResult.setMessage(e.getMessage());
        }

        return jSONResult;
    }

    /**
     * 跳转到添加属性界面
     * @param model
     * @param skuProperty
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ApiOperation(value = "跳转增加属性页面",notes = "保存表单中的填写的SKU属性",
            httpMethod = "GET",consumes = "application/x-www-form-urlencoded")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "sku对象的id",
            required = true, dataType = "Long", paramType = "form"),
            @ApiImplicitParam(name = "catalogId", value = "分类对象的id",
                    required = true, dataType = "Long", paramType = "query")
    })
       public String toPropertySave(Model model, SkuProperty skuProperty) {
        if (skuProperty.getId() != -1L) {
            skuProperty = this.skuPropertyService.getSkuProperty(skuProperty.getId());
        }

        model.addAttribute("skuProperty", skuProperty);
        return "sku/property_save";
    }

    
    @RequestMapping(value = {"/delete"}, method = {RequestMethod.GET})
    @ApiOperation(value = "删除SKU属性",notes = "保存表单中的填写的SKU属性值",
            httpMethod = "GET",consumes = "application/x-www-form-urlencoded")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "sku对象的id",
            required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "catalogId", value = "分类对象的id",
                    required = true, dataType = "Long", paramType = "query")
    })
    public String deleteSkuProperty(SkuProperty skuProperty) {
        this.skuPropertyService.delete(skuProperty.getId());
        return "redirect:/skuProperty/get/" + skuProperty.getCatalogId();
    }
}
