package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.*;
import cn.wolfcode.shop.exception.DisplayableException;
import cn.wolfcode.shop.query.ProductQueryObject;
import cn.wolfcode.shop.service.*;
import cn.wolfcode.shop.util.JSONResult;
import cn.wolfcode.shop.util.PageResult;
import cn.wolfcode.shop.util.PropertiesUtil;
import cn.wolfcode.shop.util.UploadUtil;
import cn.wolfcode.shop.viewObject.ProductInfoVo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("product")
public class ProductController {

    @Reference(timeout = 5000)
    IProductService productService;
    @Reference(timeout = 5000)
    IProductDescService productDescService;
    @Reference(timeout = 5000)
    private IProductImageService productImageService;
    @Reference(timeout = 5000)
    private ICatalogService catalogService;
    @Reference(timeout = 5000)
    private IBrandService brandService;
    @Reference(timeout = 5000)
    private IProductCatalogPropertyValueService productCatalogPropertyValueService;
    @Autowired
    private PropertiesUtil propertiesUtil;

    @RequestMapping("getAll")
    @ResponseBody
    public Object getAll() {
        List<Product> products = productService.getAll();
        return products;
    }
    @RequestMapping("get")
    public String query(Model model, @ModelAttribute("qo")ProductQueryObject qo){
        PageResult pageResult = productService.query(qo);
        model.addAttribute("pageResult",pageResult);
        return "product/product_list";
    }
    @RequestMapping("add")
    public String add(Model model,Long productId){
        List<Brand> brands = brandService.selectAll();
        List<Catalog> catalogs = catalogService.selectAll();
        model.addAttribute("brands",brands);
        model.addAttribute("catalogs",catalogs);
        if (productId!=null){
            Product product = productService.get(productId);
            ProductDesc productDesc = productDescService.get(productId);
            List<ProductImage> productImages = productImageService.getByProductId(productId);
            model.addAttribute("product",product);
            model.addAttribute("productDesc",productDesc);
            model.addAttribute("productImages",productImages);
            /*List<Map<String, Object>> properties = productCatalogPropertyValueService.getCommonPropertiesByCatalogId(product.getCatalog().getId());
            model.addAttribute("properties",properties);*/
            return "product/product_update";
        }
        return "product/product_add";
    }
    @RequestMapping("show")
    public String show(Model model,Long productId){
        List<Brand> brands = brandService.selectAll();
        List<Catalog> catalogs = catalogService.selectAll();
        model.addAttribute("brands",brands);
        model.addAttribute("catalogs",catalogs);
        Product product = productService.get(productId);
        ProductDesc productDesc = productDescService.get(productId);
        List<ProductImage> productImages = productImageService.getByProductId(productId);
        model.addAttribute("product",product);
        model.addAttribute("productDesc",productDesc);
        model.addAttribute("productImages",productImages);
        return "product/product_show";
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JSONResult saveOrUpdate(ProductInfoVo productInfoVo){
        JSONResult jsonResult = new JSONResult();
        try {
            if (productInfoVo.getProduct().getId()!=null){
                productService.update(productInfoVo);
            }else {
                productService.insert(productInfoVo);
            }
        } catch (DisplayableException e) {
            e.printStackTrace();
            jsonResult.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMessage("服务器出错了...");
        }
        return jsonResult;
    }
    @RequestMapping("basicInfo")
    public String basicInfo(Long id,Model model){
        Product product = productService.get(id);
        model.addAttribute("basic_info",product);
        return "product/basic_info";
    }

    @RequestMapping("detailInfo")
    public String detailInfo(Long id,Model model){
        ProductDesc productDesc = productDescService.get(id);
        model.addAttribute("detail_info",productDesc);
        return "product/detail_info";
    }
    @RequestMapping("imageInfo")
    public String imageInfo(Long productId,Model model){
        List<ProductImage> productImage = productImageService.getByProductId(productId);
        model.addAttribute("image_info",productImage);
        return "product/image_info";
    }
    @RequestMapping("commonPropertyInfo")
    public String commonPropertyInfo(Model model,Long catalogId){
        List<Map<String, Object>> properties = productCatalogPropertyValueService.getCommonPropertiesByCatalogId(catalogId);
        model.addAttribute("properties",properties);
        return "product/productPropertyPanel";
    }
    @RequestMapping("allPropertyInfo")
    public String allPropertyInfo(Model model,Long productId){
        List<Map<String, Object>> properties = productCatalogPropertyValueService.getAllPropertiesByProductId(productId);
        model.addAttribute("properties",properties);
        return "product/productPropertyPanel";
    }
    @RequestMapping("getAllcatalogs")
    @ResponseBody
    public List<Catalog> getAllcatalogs(){
        List<Catalog> products = catalogService.selectAll();
        return products;
    }
    @RequestMapping("upload")
    @ResponseBody
    public String upload(MultipartFile productImage){
        String imagePath = UploadUtil.upload(productImage, propertiesUtil.getUploadpath());
        return "/"+imagePath;
    }





}
