package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.CatalogProperty;
import cn.wolfcode.shop.service.ICatalogPropertyService;
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
@RequestMapping("catalogProperty")
public class CatalogPropertyController {
    @Reference
    private ICatalogPropertyService catalogPropertyService;

    @RequestMapping(method = RequestMethod.GET)
    public String getCatalogProperty() {
        return "property/property";
    }

    @RequestMapping(value = "get/{id}", method = {RequestMethod.GET})
    public String addCatalog(@PathVariable("id") Long catalogId, Model model) {
        List<CatalogProperty> catalogProperties = catalogPropertyService.selectByCatalogId(catalogId);
        model.addAttribute("list", catalogProperties);
        return "property/property_list";
    }

    /**
     * 分类属性信息回显
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String toPropertySave(Model model, CatalogProperty catalogProperty) {
        if (catalogProperty.getId() != null && catalogProperty.getId().longValue() != -1L)
            catalogProperty = catalogPropertyService.selectByPrimaryKey(catalogProperty.getId());
        model.addAttribute("catalogProperty", catalogProperty);
        return "property/property_save";
    }

    /**
     * 分类属性信息保存或者修改
     *
     * @param catalogProperty
     * @return
     */
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public JSONResult saveOrUpdate(CatalogProperty catalogProperty) {
        Long catalogId = catalogProperty.getCatalogId();
        JSONResult jsonResult = new JSONResult();
        try {
            if (catalogProperty.getId() == null) {
                int saveResult = catalogPropertyService.save(catalogProperty);
                if (saveResult == 0) {
                    throw new RuntimeException("保存分类属性信息失败");
                }
            } else if (catalogProperty.getId() != null) {
                int updataResult = catalogPropertyService.updata(catalogProperty);
                if (updataResult == 0) {
                    throw new RuntimeException("修改分类属性信息失败");
                }
            } else {
                throw new RuntimeException("上传信息有误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMessage(e.getMessage());
        }
        jsonResult.setResult(catalogId);
        return jsonResult;
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public JSONResult delete(Long id,Long catalogId) {
        JSONResult jsonResult = new JSONResult();
        try {
            if (id != null) {
                catalogPropertyService.delete(id);
            } else {
                throw new RuntimeException("删除失败");
            }
        } catch (Exception e) {
            jsonResult.setMessage(e.getMessage());
        }
        jsonResult.setResult(catalogId);
        return jsonResult;
    }
}
