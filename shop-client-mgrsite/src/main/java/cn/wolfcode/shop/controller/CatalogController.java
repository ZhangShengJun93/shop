package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.Catalog;
import cn.wolfcode.shop.service.ICatalogService;
import cn.wolfcode.shop.util.JSONResult;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("catalog")
public class CatalogController {

    @Reference(timeout = 5000)
    private ICatalogService catalogService;


      @RequestMapping(value = "getForSku", method = RequestMethod.POST)
      @ResponseBody
      public List<Map<String, Object>> getCatalog(Long id) {

          List<Map<String, Object>> childenCatalogs = catalogService.getChildenCatalogById(id);
          return childenCatalogs;
      }
    @RequestMapping(method = RequestMethod.GET)
    public String catalog() {
        
          return "catalog/catalog";
    }

    @RequestMapping(value = "get", method = RequestMethod.POST)
    @ResponseBody
    public List<Catalog> getCatalogs(Long id) {
        List<Catalog> list = catalogService.getChildenCatalogByIds(id);
        for (Catalog catalog : list) {
            System.out.println(catalog.toString());
        }
        return list;
    }

    @RequestMapping(value = "add/{id}", method = {RequestMethod.GET})
    public String addCatalog(@PathVariable("id") Long catalogId, Model model) {
        Catalog catalog = catalogService.selectByCatalogId(catalogId);
        model.addAttribute("catalog", catalog);
        return "catalog/catalog_input";
    }

    @RequestMapping(value = "delete/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public JSONResult delete(@PathVariable("id") Long catalogId) {
        JSONResult jsonResult = new JSONResult();
        try {
            if (catalogId != null) {
                int catalogReasult = catalogService.deleteByCatalogId(catalogId);
            } else {
                throw new RuntimeException("删除失败!");
            }
        } catch (Exception e) {
            jsonResult.setMessage(e.getMessage());
        }
        return jsonResult;

    }

    @RequestMapping(value = "saveOrUpdate", method = {RequestMethod.POST})
    @ResponseBody
    public JSONResult saveOrUpdate(Catalog catalog) {
        JSONResult jsonResult = new JSONResult();
        try {
            if (catalog.getId() == null) {
                int saveResult = catalogService.save(catalog);
                if (saveResult == 0) {
                    throw new RuntimeException("保存分类信息失败");
                }
            } else if (catalog.getId() != null) {
                int updataResult = catalogService.updata(catalog);
                if (updataResult == 0) {
                    throw new RuntimeException("修改分类信息失败");
                }
            } else {
                throw new RuntimeException("上传信息有误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMessage(e.getMessage());
        }
        return jsonResult;
    }

}
