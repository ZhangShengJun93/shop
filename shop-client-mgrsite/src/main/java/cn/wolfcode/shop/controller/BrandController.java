package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.Brand;
import cn.wolfcode.shop.domain.Catalog;
import cn.wolfcode.shop.query.BrandQueryObject;
import cn.wolfcode.shop.service.IBrandService;
import cn.wolfcode.shop.service.ICatalogService;
import cn.wolfcode.shop.util.JSONResult;
import cn.wolfcode.shop.util.PageResult;
import cn.wolfcode.shop.util.PropertiesUtil;
import cn.wolfcode.shop.util.UploadUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
@Controller
public class BrandController {
    @Reference(timeout = 5000)
    private IBrandService brandService;
    @Reference
    private ICatalogService catalogService;
    @Autowired
    private PropertiesUtil propertiesUtil;

    @RequestMapping("brand_list")
    public String bidrequestAudit1List(@ModelAttribute("qo") BrandQueryObject qo, Model model) {
        PageResult pageResult = brandService.query(qo);
        model.addAttribute("pageResult", pageResult);
        List<Catalog> catalogs = catalogService.getCatalog();
        model.addAttribute("catalogs", catalogs);
        return "product/brand";
    }

    @RequestMapping("brand_delete")
    @ResponseBody
    public JSONResult brandDelete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            if (id != null) {
                brandService.delete(id);
            } else {
                throw new RuntimeException("删除失败!");
            }
        } catch (Exception e) {
            jsonResult.setMessage(e.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping("brand_saveOrupdate")
    @ResponseBody
    public JSONResult saveOrUpdate(Brand brand, Long[] catIds) {
        JSONResult jsonResult = new JSONResult();
        try {
            if (brand.getId() == null) {
                brand.setCreatedDate(new Date());
                brand.setFoundDate(new Date());
                brandService.save(brand, catIds);
            }
            if (brand.getId() != null) {
                brand.setLastModifiedDate(new Date());
                brandService.updata(brand, catIds);
            }
        } catch (Exception e) {
            jsonResult.setMessage(e.getMessage());
        }
        return jsonResult;
    }

    //上传logo
    @RequestMapping("uploadLOGO")
    @ResponseBody
    public String uploadPhoto(MultipartFile file) {
        String filePath = UploadUtil.upload(file, propertiesUtil.getUploadpath());
        brandService.savePhoto(filePath);
        return filePath;
    }
    @RequestMapping("getAllBrands")
    @ResponseBody
    public List<Brand> getAllBrands(){
        List<Brand> brands = brandService.selectAll();
        return brands;
    }
}
